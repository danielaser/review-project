package org.example;

import org.example.command.utils.ConsoleHandler;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleHandlerTest {

    private ConsoleHandler consoleHandler;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        // Redirigir salida estándar
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    void tearDown() {
        // Restaurar salida estándar
        System.setOut(System.out);
    }

    @Test
    @DisplayName("Test leer línea desde consola")
    void testReadLine() {
        // Redirigir entrada estándar
        String input = "Test input\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        consoleHandler = new ConsoleHandler();
        String result = consoleHandler.readLine();

        assertEquals("Test input", result, "El texto leído desde la consola no coincide.");
    }

    @Test
    @DisplayName("Test escribir línea a la consola")
    void testWriteLine() {
        consoleHandler = new ConsoleHandler();
        String message = "Hello, Console!";

        consoleHandler.writeLine(message);

        String output = outputStream.toString().trim();
        assertEquals("Hello, Console!", output, "El mensaje escrito en la consola no coincide.");
    }
}
