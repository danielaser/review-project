package org.example.observerTest;

import org.example.observer.IObservable;
import org.example.observer.TheObserver;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class TheObserverTest {

    @Test
    @DisplayName("El observador debe recibir la notificación")
    void updateTest() {
        TheObserver observer = new TheObserver();

        IObservable observable = mock(IObservable.class);

        String message = "Mensaje de prueba";

        observable.notifyObservers(message);

        TheObserver spyObserver = spy(observer);
        spyObserver.update(message);

        verify(spyObserver, times(1)).update(message);
    }
}
