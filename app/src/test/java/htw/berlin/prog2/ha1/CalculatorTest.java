package htw.berlin.prog2.ha1;

// Paul Dudek s0563692 16.04.2024

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Retro calculator")
class CalculatorTest {

    @Test
    @DisplayName("should display result after adding two positive multi-digit numbers")
    void testPositiveAddition() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "40";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display result after getting the square root of two")
    void testSquareRoot() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressUnaryOperationKey("√");

        String expected = "1.41421356";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when dividing by zero")
    void testDivisionByZero() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressBinaryOperationKey("/");
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should display error when drawing the square root of a negative number")
    void testSquareRootOfNegative() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(7);
        calc.pressNegativeKey();
        calc.pressUnaryOperationKey("√");

        String expected = "Error";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("should not allow multiple decimal dots")
    void testMultipleDecimalDots() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(1);
        calc.pressDotKey();
        calc.pressDigitKey(7);
        calc.pressDotKey();
        calc.pressDigitKey(8);

        String expected = "1.78";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    // Aufgabe 1 Neuer Grüner Test
    @Test
    @DisplayName("should display result after multiplying two positive multi-digit numbers")
    void testPositiveMultiplication() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(3);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("x");  // Zuvor ungetestete Funktionalität
        calc.pressDigitKey(3);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        String expected = "900";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }

    // Aufgabe 2 Roter Test
    @Test
    @DisplayName("should handle screen value, latest operation and latest value correctly when clearing the screen twice after an addition")
    void testDoubleClearScreenAfterAddition() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(2);
        calc.pressDigitKey(0);
        calc.pressEqualsKey();

        calc.pressClearKey();                                   // First Clear Press

        String expectedScreenFirstClear = "0";                  // 0 wird angezeigt
        double expectedLatestValueFirstClear = 40.0;            // Letzter Wert bleibt erhalten
        String expectedLatestOperationFirstClear = "+";         // Letzter Operator bleibt erhalten

        String actualScreen = calc.readScreen();
        double actualLatestValue = calc.readLatestValue();
        String actualLatestOperation = calc.readLatestOperation();

        assertEquals(expectedScreenFirstClear, actualScreen);
        assertEquals(expectedLatestValueFirstClear, actualLatestValue);
        assertEquals(expectedLatestOperationFirstClear, actualLatestOperation);

        calc.pressClearKey();                                   // Second Clear Press

        String expectedScreenSecondClear = "0";                 // 0 wird angezeigt
        double expectedLatestValueSecondClear = 0.0;            // Letzter Wert zurückgesetzt
        String expectedLatestOperationSecondClear = "";         // Letzter Operator zurückgesetzt

        actualScreen = calc.readScreen();
        actualLatestValue = calc.readLatestValue();
        actualLatestOperation = calc.readLatestOperation();

        assertEquals(expectedScreenSecondClear, actualScreen);
        assertEquals(expectedLatestValueSecondClear, actualLatestValue);
        assertEquals(expectedLatestOperationSecondClear, actualLatestOperation);
    }

    @Test
    @DisplayName("should display result after adding two positive multi-digit floating point numbers without rounding errors")
    void testPositiveAdditionFloat() {
        Calculator calc = new Calculator();

        calc.pressDigitKey(0);
        calc.pressDotKey();
        calc.pressDigitKey(0);
        calc.pressDigitKey(1);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(0);
        calc.pressDotKey();
        calc.pressDigitKey(0);
        calc.pressDigitKey(2);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(0);
        calc.pressDotKey();
        calc.pressDigitKey(0);
        calc.pressDigitKey(3);
        calc.pressBinaryOperationKey("+");
        calc.pressDigitKey(0);
        calc.pressDotKey();
        calc.pressDigitKey(0);
        calc.pressDigitKey(4);
        calc.pressEqualsKey();

        String expected = "0.1";
        String actual = calc.readScreen();

        assertEquals(expected, actual);
    }


}

