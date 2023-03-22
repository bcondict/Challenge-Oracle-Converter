import javax.swing.JOptionPane;

public class Menu {
    public static void main(String[] args) {

        String conversor;
        float temp_val = 0;
        float amount = 0;

        // first option to convert
        Object[] menu = new Object[] {
            "Conversor de Moneda",
            "Conversor de Temperatura"
        };

        // different strings for options
        String select_menu = "Seleccione una opción de conversión";
        String amountString = "Ingrese la cantidad de _ que desaea convertir";
        String select_currency = "Elije una moneda a la que deseas convertir tu dinero";
        String select_temp = "Elije una temperatura a la que deseas convertir";

        // all available currency
        Object[] currency = new Object[] {
            "De Pesos a Dolares",
            "De pesos a Euros",
            "De pesos a Libras",
            "De pesos a Yenes",
            "De pesos a Wones Coreano",
            "De Dólarese a Pesos",
            "De Euros a Pesos",
            "De Libras a pesos",
            "De Yenes a pesos",
            "De Wones Coreano a pesos"
        };

        // all available temperature scale
        Object[] temp_scale = new Object[] {
            "Grados Celsius a Fahrenheit",
            "Grados Celsius a Kelvin",
            "Grados Fahrenheit a Celsius",
            "Kelvin a Grados Celsius",
            "Kelvin a Grados Fahrenheit"
        };

        boolean status_exit = true;

        // infinite loop, exit on user confirmation
        while (status_exit){

            // choose option to convert
            conversor = JOptionPane.showInputDialog(
                null, select_menu, "Menu",
                JOptionPane.QUESTION_MESSAGE, null, menu, "Conversor de Moneda"
            ).toString();

            // to convert currency
            if (conversor.equals("Conversor de Moneda")) {

                try {
                    // get amount to convert
                    amount = Float.parseFloat(
                        // message to show, ingrese la cantidad de dinero que desea convertir
                        JOptionPane.showInputDialog(null, amountString.replace("_", "dinero"))
                        );
                }
                catch (NumberFormatException e) {
                    // show error message if user input is not a number
                    JOptionPane.showMessageDialog(null, "Valor no válido", "Message", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                // currency to convert, get from user, default value is "De Pesos a Dolar"
                String currency_to_convert = JOptionPane.showInputDialog(
                    null, select_currency, "Monedas",
                    JOptionPane.QUESTION_MESSAGE, null, currency, "De Pesos a Dolar"
                ).toString();

                // takes the value of the currency to convert
                // eg: "De Pesos a Dolares" -> "Dolares"
                String[] value = currency_to_convert.split(" ");

                // show result of conversion
                // eg: "Tienes $ 0.00021 Dolares"
                JOptionPane.showInternalMessageDialog(
                    null,
                    "Tienes $ " +
                    currency_calculator(amount, currency_to_convert, currency) + " " +
                    value[3]
                );
            }

            // to convert temperature
            if (conversor.equals("Conversor de Temperatura")) {

                try {
                    // temperature to convert
                    temp_val = Float.parseFloat(
                        JOptionPane.showInputDialog(null, amountString.replace("_", "temperatura"))
                    );
                }
                catch (NumberFormatException e) {
                    // show error message if user input is not a number
                    JOptionPane.showMessageDialog(null, "Valor no válido", "Message", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                // scale to convert
                String scale_to_convert = JOptionPane.showInputDialog(
                    null, select_temp, "Temperaturas",
                    JOptionPane.QUESTION_MESSAGE, null, temp_scale, "Grados Celsius a Fahrenheit" 
                ).toString();

                // takes the value of the scale to convert
                // eg: "Grados Celsius a Fahrenheit" -> "Grados" "Celsius" "Fahrenheit"
                String[] grades = scale_to_convert.split(" ");

                // show result of conversion
                // eg: "0 Grados Celsius son 32 Fahrenheit"
                JOptionPane.showInternalMessageDialog(
                    null,
                    temp_val + " " +
                    grades[0] + " " +
                    grades[1] + " son " +
                    temp_calculator(temp_val, scale_to_convert) + " " +
                    grades[3] 
                    );
            }

            // confirm to exit
            int confirm = JOptionPane.showConfirmDialog(null, "¿Deseas continuar?");

            // if user confirm, exit break infinite loop
            if (confirm == 1)
                status_exit = false;
        }

        // show message when program is finished
        JOptionPane.showMessageDialog(null, "Programa finalizado", "Message", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * @param value value of currency to convert
     * @param currency expected output currency
     * @param all_currency list of all currency
     * @return value calculated of new currency
     */
    public static float currency_calculator(float value, String currency, Object[] all_currency) {
        float result = 0;

        if (currency.equals(all_currency[0]))
            result = (float) (value * 0.00021);
        if (currency.equals(all_currency[1]))
            result = (float) (value * 0.00019);
        if (currency.equals(all_currency[2]))
            result = (float) (value * 0.00017);
        if (currency.equals(all_currency[1]))
            result = (float) (value * 0.027);
        if (currency.equals(all_currency[4]))
            result = (float) (value * 0.27);
        if (currency.equals(all_currency[5]))
            result = (float) (value * 4820.4);
        if (currency.equals(all_currency[6]))
            result = (float) (value * 5191.71);
        if (currency.equals(all_currency[7]))
            result = (float) (value * 5887.3);
        if (currency.equals(all_currency[8]))
            result = (float) (value * 36.37);
        if (currency.equals(all_currency[9]))
            result = (float) (value * 3.68);

        return result;
    }

    /**
     * @param temp_val: value of temperature to calculate
     * @param scale: sacale  to calculate
     * @return result of operation
     */
    public static float temp_calculator(float temp_val, String scale) {
        float result = 0;

        if (scale.equals("Grados Celsius a Fahrenheit"))
            result = (float) (temp_val * 1.8 + 32);
        if (scale.equals("Grados Fahrenheit a Celsius"))
            result = (float) ((temp_val - 32) * (5 / 9));

        if (scale.equals("Grados Celsius a Kelvin"))
            result = (float) (temp_val + 273.15);
        if (scale.equals("Grados Kelvin a Celsius"))
            result = (float) (temp_val - 273.15);

        if (scale.equals("Grados Kelvin a Fahrenheit"))
            result = (float) ((temp_val - 273.15) * 1.8 + 32);

        return result;
    }
}
