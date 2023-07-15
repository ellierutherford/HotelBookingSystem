<div>
    <form action="${actionUrl}" method="post" modelAttribute="card">
        <table >
            <caption>
                <h2> Your Details </h2>
            </caption>
            <tr>
                <th>Card Holder name:</th>
                <td>
                    <input type="text" name="cardHolderName" size="45"
                           value="<c:out value='${card.cardHolderName}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>Card Number: </th>
                <td>
                    <input type="text" name="cardNumber" size="45"
                           value="<c:out value='${card.cardNumber}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>Cvv: </th>
                <td>
                    <input type="text" name="cvv" size="45"
                           value="<c:out value='${card.cvv}' />"
                    />
                </td>
            </tr>

            <tr>
                <th>Expiry date: </th>
                <td>
                    <input type="text" name="expiry" placeholder="MM/YY"
                           pattern="([0-9]{2}[/]?){2}" value="<c:out value='${card.expiry}' />"
                    />
                </td>
            </tr>

            <tr>
                <td colspan="2" >
                    <input type="submit" id="saveButton" value="Save" />
            </tr>
        </table>
    </form>
</div>
