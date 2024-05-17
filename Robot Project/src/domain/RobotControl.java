package domain;


/**
 * Das Interface repräsentiert einen einfachen Roboter mit seinen Funktionen.
 *
 * Jeder produzierte Roboter hat einen Namen, der vom Besteller frei gewählt
 * werden kann. Der Name bleibt über die gesamte Lebensdauer des Roboters
 * unveränderlich. Man kann einen Roboter jederzeit über die
 * <code>getName()</code>-Methode nach seinem Namen fragen.
 *
 * Zusätzlich zum frei gewählten Namen, hat jeder Roboter noch eine
 * Seriennummer. Diese wird bei der Produktion festgelegt und hat einen vom
 * Roboter-Typ abhängigen Bereich möglicher Werte. Innerhalb des Bereiches wird
 * die Seriennummer zufällig vergeben. Die Seriennummer kann auch bei
 * ausgeschalteten Roboter über <code>getId()</code>gelesen werden.
 *
 * Ein Roboter hat einen Hauptschalter, der mithilfe der
 * <code>triggerPowerSwitch()</code>-Methode bedient werden kann. Direkt nach
 * der Produktion ist der Roboter ausgeschaltet. Drückt man einmal auf den
 * Schalter, wird er eingeschaltet. Ein weiterer Druck schaltet ihn wieder aus, usw.
 *
 * Die aktuelle Position des Hauptschalters kann man mit der Methode
 * <code>isPowerOn()</code> abfragen. Hierbei bedeutet <code>true</code>, dass
 * der Roboter eingeschaltet ist und <code>false</code>, dass er nicht
 * eingeschaltet ist.
 *
 * Falls ein Fehler auftritt, kann der Nutzer des Roboters den letzten
 * aufgetretenen Fehler über eine Blackbox (Fehlerspeicher) auslesen. Dies
 * geschieht mithilfe der <code>getLastException()</code>-Methode. Der
 * Fehlerspeicher kann auch bei ausgeschaltetem Roboter benutzt werden. Gab es
 * noch keinen Fehler, ist der Fehlerspeicher leer (<code>null</code>).
 *
 * Alle Methoden dieses Interfaces können auch auf einem Roboter aufgerufen
 * werden, der ausgeschaltet ist (d.h. wenn <code>isPowerOn()</code> == false).
 */
public interface RobotControl {

    /**
     * Gibt die ID (Seriennummer) des Roboters zurück.
     *
     * @return Eine eindeutige Identifikation in Form einer Zahl.
     */
    public int getId();

    /**
     * Gibt den Namen des Roboter-Exemplars zurück.
     *
     * @return Der Name des Roboters.
     */
    public String getName();

    /**
     * Betätigen den An-/Ausschaltknopf.
     */
    public void triggerPowerSwitch();

    /**
     * Prüft ob der Roboter eingeschaltet ist.
     *
     * @return <code>true</code> bedeutet, dass der Roboter eingeschaltet ist,
     *      <code>false</code>, dass er nicht eingeschaltet ist.
     */
    public boolean isPowerOn();

    /**
     * Ruft die zuletzt aufgetretene Ausnahme aus der Blackbox ab.
     *
     * @return zuletzt aufgetretene Ausnahme oder <code>null</code> falls noch
     *         keine aufgetreten ist.
     */
    public RobotException getLastException();
}