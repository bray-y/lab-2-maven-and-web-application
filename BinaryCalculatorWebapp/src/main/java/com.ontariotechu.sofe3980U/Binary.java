package com.ontariotechu.sofe3980U;

/**
 * Unsigned integer Binary variable
 *
 */
public class Binary {
    private String number = "0"; // string containing the binary value '0' or '1'

    public Binary(String number) {
        if (number == null || number.isEmpty()) {
            this.number = "0";
            return;
        }

        for (int i = 0; i < number.length(); i++) {
            char ch = number.charAt(i);
            if (ch != '0' && ch != '1') {
                this.number = "0";
                return;
            }
        }

        int beg;
        for (beg = 0; beg < number.length(); beg++) {
            if (number.charAt(beg) != '0') {
                break;
            }
        }

        this.number = (beg == number.length()) ? "0" : number.substring(beg);
        if (this.number.isEmpty()) {
            this.number = "0";
        }
    }

    public String getValue() {
        return this.number;
    }

    public static Binary add(Binary num1, Binary num2) {
        int ind1 = num1.number.length() - 1;
        int ind2 = num2.number.length() - 1;
        int carry = 0;
        String num3 = "";

        while (ind1 >= 0 || ind2 >= 0 || carry != 0) {
            int sum = carry;
            if (ind1 >= 0) {
                sum += (num1.number.charAt(ind1) == '1') ? 1 : 0;
                ind1--;
            }
            if (ind2 >= 0) {
                sum += (num2.number.charAt(ind2) == '1') ? 1 : 0;
                ind2--;
            }
            carry = sum / 2;
            sum = sum % 2;
            num3 = ((sum == 0) ? "0" : "1") + num3;
        }

        return new Binary(num3);
    }

    public static Binary or(Binary num1, Binary num2) {
        int maxLength = Math.max(num1.number.length(), num2.number.length());
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < maxLength; i++) {
            char bit1 = (i < maxLength - num1.number.length()) ? '0' : num1.number.charAt(i - (maxLength - num1.number.length()));
            char bit2 = (i < maxLength - num2.number.length()) ? '0' : num2.number.charAt(i - (maxLength - num2.number.length()));

            result.append((bit1 == '1' || bit2 == '1') ? '1' : '0');
        }

        return new Binary(result.toString());
    }

    public static Binary and(Binary num1, Binary num2) {
        int maxLength = Math.max(num1.number.length(), num2.number.length());
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < maxLength; i++) {
            char bit1 = (i < maxLength - num1.number.length()) ? '0' : num1.number.charAt(i - (maxLength - num1.number.length()));
            char bit2 = (i < maxLength - num2.number.length()) ? '0' : num2.number.charAt(i - (maxLength - num2.number.length()));

            result.append((bit1 == '1' && bit2 == '1') ? '1' : '0');
        }

        return new Binary(result.toString());
    }

    public static Binary multiply(Binary num1, Binary num2) {
        Binary result = new Binary("0");
        Binary tempNum2 = new Binary(num2.number);

        for (int i = num1.number.length() - 1; i >= 0; i--) {
            if (num1.number.charAt(i) == '1') {
                result = add(result, tempNum2);
            }
            tempNum2 = new Binary(tempNum2.number + "0");
        }

        return result;
    }

    public static void testOr() {
        Binary b1 = new Binary("1010");
        Binary b2 = new Binary("1100");
        System.out.println("OR Test: " + or(b1, b2).getValue().equals("1110"));
    }

    public static void testAnd() {
        Binary b1 = new Binary("1010");
        Binary b2 = new Binary("1100");
        System.out.println("AND Test: " + and(b1, b2).getValue().equals("1000"));
    }

    public static void testMultiply() {
        Binary b1 = new Binary("101");
        Binary b2 = new Binary("11");
        System.out.println("Multiply Test: " + multiply(b1, b2).getValue().equals("1111"));
    }
}