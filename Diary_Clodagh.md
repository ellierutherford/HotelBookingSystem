Starting diary again on fresh branch.

Finally working 13.07.23 after HUNDREDS (690 Pages of screenshots) of restarts and re installs

Learning - If you dont code day by day the learning curve is akin to learning a completely new language, e.g. operating in russian never having learned russian before and without haveing any direction on the grammer. 
Security professionals know the general frameworks and analyse code for flaws, however rarely code full time as it is prohibitively expensive and not a great use of resources - possibly a conflict of interest (you cant be objective on code flaws you might have created) of resources
Turns out there was a conflict with visual studio install I had added on matts recommendation as thats how he got everything working so easily, i just had tpp many othere Ellie helped me hugely with the sql startup code so team have been great
1507.have added some entries,
theres a radio button to nowhere on newroomasset will see if I can fix and
whitelabel error pops up on save will see if I can get a funkier and more informative error page
It can be disabled in applicationproperties using spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration
That worked better now get a tomcat 404 error message still ugly just not as long so will try to replace page will try some of the code inspection tool I found earlier listed in
Installed Zap to test and trying through Kali toolset I have on virtualbox.
At moments seeing how to resolve whitelabel /error page when you click on save on roomassets
Question - If I have port 8080 open through SQL on my local machine - doesnt that mean my local machine 10.0.2.2 on a local network arp is exposed through my tethered mobile internet public IP
will try to run some of these before submit

Storing it here


Code Validation
1. Validation with Spring Boot - the Complete Guide (reflectoring.io)  https://reflectoring.io/bean-validation-with-spring-boot
2. validating FROM INPUT Getting Started | Validating Form Input (spring.io) https://spring.io/guides/gs/validating-form-input/
3. Spring boot starter validation with Maven Maven Repository: org.springframework.boot » spring-boot-starter-validation (mvnrepository.com) https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-validation
4. Validation by Using Spring’s Validator Interface :: Spring Framework https://docs.spring.io/spring-framework/reference/core/validation/validator.html

Security

1. Maven Spring Security core https://mvnrepository.com/artifact/org.springframework.security/spring-security-core
This is the one on Baedlung too
2. Base Maven Security https://mvnrepository.com/tags/security
3. Jetbrains Godana  Static Code analysis tool   JetBrains https://www.jetbrains.com/qodana/
4. Spring security core https://mvnrepository.com/artifact/org.springframework.security/spring-security-core
Spring Security is a powerful and highly customizable authentication and access-control framework. It provides protection against attacks like session fixation, clickjacking, cross site request forgery, etc

Testing
Webgoat

Maven Password encryption

Password Encryption
Introduction
How to create a master password
How to encrypt server passwords
How to keep the master password on removable drive
Tips
Introduction
Maven supports server password encryption. The main use case, addressed by this solution is:

multiple users share the same build machine (server, CI box)
some users have the privilege to deploy Maven artifacts to repositories, some don't.
this applies to any server operations, requiring authorization, not only deployment
settings.xml is shared between users
The implemented solution adds the following capabilities:

authorized users have an additional settings-security.xml file in their ${user.home}/.m2 directory
this file either contains encrypted master password, used to encrypt other passwords
or it can contain a relocation - reference to another file, possibly on removable storage
this password is created first via CLI for now
server entries in the settings.xml have passwords and/or keystore passphrases encrypted
for now - this is done via CLI after master password has been created and stored in appropriate location
How to create a master password
Use the following command line:

mvn --encrypt-master-password <password>
Note: Since Maven 3.2.1 the password argument should no longer be used (see Tips below for more information). Maven will prompt for the password. Earlier versions of Maven will not prompt for a password, so it must be typed on the command-line in plaintext.

This command will produce an encrypted version of the password, something like

{jSMOWnoPFgsHVpMvz5VrIt5kRbzGpI8u+9EF1iFQyJQ=}
Store this password in the ${user.home}/.m2/settings-security.xml; it should look like

<settingsSecurity>
  <master>{jSMOWnoPFgsHVpMvz5VrIt5kRbzGpI8u+9EF1iFQyJQ=}</master>
</settingsSecurity>
When this is done, you can start encrypting existing server passwords.

How to encrypt server passwords
You have to use the following command line:

mvn --encrypt-password <password>
Note:Just like --encrypt-master-password the password argument should no longer be used since Maven 3.2.1 (see Tips below for more information.).

This command produces an encrypted version of it, something like

{COQLCE6DU6GtcS5P=}
Copy and paste it into the servers section of your settings.xml file. This will look like:

<settings>
...
  <servers>
...
    <server>
      <id>my.server</id>
      <username>foo</username>
      <password>{COQLCE6DU6GtcS5P=}</password>
    </server>
...
  </servers>
...
</settings>
Please note that password can contain any information outside of the curly brackets, so that the following will still work:

<settings>
...
  <servers>
...
    <server>
      <id>my.server</id>
      <username>foo</username>
      <password>Oleg reset this password on 2009-03-11, expires on 2009-04-11 {COQLCE6DU6GtcS5P=}</password>
    </server>
...
  </servers>
...
</settings>
Then you can use, say, deploy plugin, to write to this server:

mvn deploy:deploy-file -Durl=https://maven.corp.com/repo \
                       -DrepositoryId=my.server \
                       -Dfile=your-artifact-1.0.jar \
How to keep the master password on removable drive
Create the master password exactly as described above, and store it on a removable drive, for instance on OSX, my USB drive mounts as /Volumes/mySecureUsb, so I store

<settingsSecurity>
  <master>{jSMOWnoPFgsHVpMvz5VrIt5kRbzGpI8u+9EF1iFQyJQ=}</master>
</settingsSecurity>
in the file /Volumes/mySecureUsb/secure/settings-security.xml

And then I create ${user.home}/.m2/settings-security.xml with the following content:

<settingsSecurity>
  <relocation>/Volumes/mySecureUsb/secure/settings-security.xml</relocation>
</settingsSecurity>
This assures that encryption only works when the USB drive is mounted by the OS. This addresses a use case where only certain people are authorized to deploy and are issued these devices.

Tips
Escaping curly-brace literals in your password (Since: Maven 2.2.0)
At times, you might find that your password (or the encrypted form of it) contains '{' or '}' as a literal value. If you added such a password as-is to your settings.xml file, you would find that Maven does strange things with it. Specifically, Maven treats all the characters preceding the '{' literal, and all the characters after the '}' literal, as comments. Obviously, this is not the behavior you want. What you really need is a way of escaping the curly-brace literals in your password.

You can do this with the widely used '\' escape character. If your password looks like this:

jSMOWnoPFgsHVpMvz5VrIt5kRbzGpI8u+{EF1iFQyJQ=
Then, the value you would add to your settings.xml looks like this:

{jSMOWnoPFgsHVpMvz5VrIt5kRbzGpI8u+\{EF1iFQyJQ=}
Password Security
Editing settings.xml and running the above commands can still leave your password stored locally in plaintext. You may want to check the following locations:

Shell history (e.g. by running history). You may want to clear your history after encrypting the above passwords
Editor caches (e.g. ~/.viminfo)
Also note that the encrypted passwords can be decrypted by someone that has the master password and settings security file. Keep this file secure (or stored separately) if you expect the possibility that the settings.xml file may be retrieved.

Password Escaping on different platforms
On some platforms it might be necessary to quote the password if it contains special characters like %, !, $, etc. For example on Windows you have to be careful about things like the following:

The following example will not work on Windows:

mvn --encrypt-master-password a!$%^b
whereas the following will work on Windows:

mvn --encrypt-master-password "a!$%^b"
If you are on a linux/unix platform you should use single quotes for the above master password. Otherwise the master password will not work (caused by the dollar sign and the exclamation mark).

Prompting for Password
In Maven before version 3.2.1 you have to give the password on the command line as an argument which means you might need to escape your password. In addition usually the shell stores the full history of commands you have entered, therefore anyone with access to your computer could restore the password from the shell`s history.

Starting with Maven 3.2.1, the password is an optional argument. If you omit the password, you will be prompted for it which prevents all the issues mentioned above.

We strongly recommend using Maven 3.2.1 and above to prevent problems with escaping special characters and of course security issues related to bash history or environment issues in relationship with the password.





Security and Deployment Settings
Repositories to deploy to are defined in a project in the distributionManagement section. However, you cannot put your username, password, or other security settings in that project. For that reason, you should add a server definition to your own settings with an id that matches that of the deployment repository in the project.

In addition, some repositories may require authorisation to download from, so the corresponding settings can be specified in a server element in the same way.

Which settings are required will depend on the type of repository you are deploying to. As of the first release, only SCP deployments and file deployments are supported by default, so only the following SCP configuration is needed:

<settings>
  .
  .
  <servers>
    <server>
      <id>repo1</id>
      <username>repouser</username>
      <!-- other optional elements:
        <password>my_login_password</password>
        <privateKey>/path/to/identity</privateKey> (default is ~/.ssh/id_dsa)
        <passphrase>my_key_passphrase</passphrase>
      -->
    </server>
  </servers>
  .
  .
</settings>
To encrypt passwords in these sections, refer to Encryption Settings.

Note: The settings descriptor documentation can be found on the Maven Local Settings Model Website.

3. 

Spring features a Validator interface that you can use to validate objects. The Validator interface works by using an Errors object so that, while validating, validators can report validation failures to the Errors object.

Consider the following example of a small data object

Validation using Springs Validator interface  interface public class Person {

	private String name;
	private int age;

	// the usual getters and setters...


The next example provides validation behavior for the Person class by implementing the following two methods of the org.springframework.validation.Validator interface:

supports(Class): Can this Validator validate instances of the supplied Class?

validate(Object, org.springframework.validation.Errors): Validates the given object and, in case of validation errors, registers those with the given Errors object.

Implementing a Validator is fairly straightforward, especially when you know of the ValidationUtils helper class that the Spring Framework also provides. The following example implements Validator for Person instances:

Java

Kotlin

public class PersonValidator implements Validator {

	/**
	 * This Validator validates only Person instances
	 */
	public boolean supports(Class clazz) {
		return Person.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
		Person p = (Person) obj;
		if (p.getAge() < 0) {
			e.rejectValue("age", "negativevalue");
		} else if (p.getAge() > 110) {
			e.rejectValue("age", "too.darn.old");
		}
	}
}

Copied!
The static rejectIfEmpty(..) method on the ValidationUtils class is used to reject the name property if it is null or the empty string. Have a look at the ValidationUtils javadoc to see what functionality it provides besides the example shown previously.

While it is certainly possible to implement a single Validator class to validate each of the nested objects in a rich object, it may be better to encapsulate the validation logic for each nested class of object in its own Validator implementation. A simple example of a “rich” object would be a Customer that is composed of two String properties (a first and a second name) and a complex Address object. Address objects may be used independently of Customer objects, so a distinct AddressValidator has been implemented. If you want your CustomerValidator to reuse the logic contained within the AddressValidator class without resorting to copy-and-paste, you can dependency-inject or instantiate an AddressValidator within your CustomerValidator, as the following example shows:

Java

Kotlin

public class CustomerValidator implements Validator {

	private final Validator addressValidator;

	public CustomerValidator(Validator addressValidator) {
		if (addressValidator == null) {
			throw new IllegalArgumentException("The supplied [Validator] is " +
				"required and must not be null.");
		}
		if (!addressValidator.supports(Address.class)) {
			throw new IllegalArgumentException("The supplied [Validator] must " +
				"support the validation of [Address] instances.");
		}
		this.addressValidator = addressValidator;
	}

	/**
	 * This Validator validates Customer instances, and any subclasses of Customer too
	 */
	public boolean supports(Class clazz) {
		return Customer.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "field.required");
		Customer customer = (Customer) target;
		try {
			errors.pushNestedPath("address");
			ValidationUtils.invokeValidator(this.addressValidator, customer.getAddress(), errors);
		} finally {
			errors.popNestedPath();
		}
	}
}

Validation errors are reported

1. Validation with Spring Boot - the Complete Guide (reflectoring.io)
2. validating FROM INPUT Getting Started | Validating Form Input (spring.io)
3. Spring boot starter validation with Maven Maven Repository: org.springframework.boot » spring-boot-starter-validation (mvnrepository.com)
4. Validation by Using Spring’s Validator Interface :: Spring Framework

Spring features a Validator interface that you can use to validate objects. The Validator interface works by using an Errors object so that, while validating, validators can report validation failures to the Errors object.
Consider the following example of a small data object
Validation using Springs Validator interface  interface public class Person {

	private String name;
	private int age;

	// the usual getters and setters...


The next example provides validation behavior for the Person class by implementing the following two methods of the org.springframework.validation.Validator interface:
supports(Class): Can this Validator validate instances of the supplied Class?
validate(Object, org.springframework.validation.Errors): Validates the given object and, in case of validation errors, registers those with the given Errors object.
Implementing a Validator is fairly straightforward, especially when you know of the ValidationUtils helper class that the Spring Framework also provides. The following example implements Validator for Person instances:
Java
Kotlin
public class PersonValidator implements Validator {

	/**
	 * This Validator validates only Person instances
	 */
	public boolean supports(Class clazz) {
		return Person.class.equals(clazz);
	}

	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
		Person p = (Person) obj;
		if (p.getAge() < 0) {
			e.rejectValue("age", "negativevalue");
		} else if (p.getAge() > 110) {
			e.rejectValue("age", "too.darn.old");
		}
	}
}

Copied!
The static rejectIfEmpty(..) method on the ValidationUtils class is used to reject the name property if it is null or the empty string. Have a look at the ValidationUtils javadoc to see what functionality it provides besides the example shown previously.
While it is certainly possible to implement a single Validator class to validate each of the nested objects in a rich object, it may be better to encapsulate the validation logic for each nested class of object in its own Validator implementation. A simple example of a “rich” object would be a Customer that is composed of two String properties (a first and a second name) and a complex Address object. Address objects may be used independently of Customer objects, so a distinct AddressValidator has been implemented. If you want your CustomerValidator to reuse the logic contained within the AddressValidator class without resorting to copy-and-paste, you can dependency-inject or instantiate an AddressValidator within your CustomerValidator, as the following example shows:
Java
Kotlin
public class CustomerValidator implements Validator {

	private final Validator addressValidator;

	public CustomerValidator(Validator addressValidator) {
		if (addressValidator == null) {
			throw new IllegalArgumentException("The supplied [Validator] is " +
				"required and must not be null.");
		}
		if (!addressValidator.supports(Address.class)) {
			throw new IllegalArgumentException("The supplied [Validator] must " +
				"support the validation of [Address] instances.");
		}
		this.addressValidator = addressValidator;
	}

	/**
	 * This Validator validates Customer instances, and any subclasses of Customer too
	 */
	public boolean supports(Class clazz) {
		return Customer.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "field.required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "surname", "field.required");
		Customer customer = (Customer) target;
		try {
			errors.pushNestedPath("address");
			ValidationUtils.invokeValidator(this.addressValidator, customer.getAddress(), errors);
		} finally {
			errors.popNestedPath();
		}
	}
}

Copied!
Validation errors are reported to the Errors object passed to the validator. In the case of Spring Web MVC, you can use the <spring:bind/> tag to inspect the error messages, but you can also inspect the Errors object yourself. More information about the methods it offers can be found in the javadoc.}
