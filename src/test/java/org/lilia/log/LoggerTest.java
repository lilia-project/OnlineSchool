package org.lilia.log;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LoggerTest {
    @Mock
    LogStorage logStorage;
    Logger target;
    @Captor
    ArgumentCaptor<Log> captor;

    @BeforeEach
    void setUp() {
        target = new Logger("className", logStorage);
    }

    @Test
    void shouldPrintErrorMessage() {
        target.error("Some message");

        verify(logStorage).save(captor.capture());
        Log captorValue = captor.getValue();
        assertEquals("className", captorValue.getName());
        assertEquals("Some message", captorValue.getMessage());
        assertEquals("ERROR", captorValue.getLogLevel());
        assertNotNull(captorValue.getCreatedAt());
    }

    @Test
    void shouldPrintErrorMessageWithRedString() {
        RuntimeException exception = new RuntimeException("Red color");
        target.error("Other message", exception);

        verify(logStorage).save(captor.capture());
        Log captorValue = captor.getValue();
        assertEquals("className", captorValue.getName());
        assertEquals("Other message", captorValue.getMessage());
        assertEquals("ERROR", captorValue.getLogLevel());
        assertEquals(exception.toString(), captorValue.getStackTrace());
        assertNotNull(captorValue.getCreatedAt());

    }

    @Test
    void shouldPrintWarningMessage() {
        target.warning("Some message");

        verify(logStorage).save(captor.capture());
        Log captorValue = captor.getValue();
        assertEquals("className", captorValue.getName());
        assertEquals("Some message", captorValue.getMessage());
        assertEquals("WARNING", captorValue.getLogLevel());
        assertNotNull(captorValue.getCreatedAt());
    }

    @Test
    void shouldPrintWarningMessageWithRedString() {
        RuntimeException exception = new RuntimeException("Red color");
        target.warning("Other message", exception);

        verify(logStorage).save(captor.capture());
        Log captorValue = captor.getValue();
        assertEquals("className", captorValue.getName());
        assertEquals("Other message", captorValue.getMessage());
        assertEquals("WARNING", captorValue.getLogLevel());
        assertEquals(exception.toString(), captorValue.getStackTrace());
        assertNotNull(captorValue.getCreatedAt());
    }

    @Test
    void shouldPrintInfoMessage() {
        target.info("Some message");

        verify(logStorage).save(captor.capture());
        Log captorValue = captor.getValue();
        assertEquals("className", captorValue.getName());
        assertEquals("Some message", captorValue.getMessage());
        assertEquals("INFO", captorValue.getLogLevel());
        assertNotNull(captorValue.getCreatedAt());
    }

    @Test
    void shouldPrintDebugMessage() {
        target.debug("Debug message");

        verify(logStorage).save(captor.capture());
        Log captorValue = captor.getValue();
        assertEquals("className", captorValue.getName());
        assertEquals("Debug message", captorValue.getMessage());
        assertEquals("DEBUG", captorValue.getLogLevel());
        assertNotNull(captorValue.getCreatedAt());
    }
}