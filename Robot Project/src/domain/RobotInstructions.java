package domain;


//import tpe.exceptions.roboter.exceptions.RobotException;
//import tpe.exceptions.roboter.exceptions.RobotIllegalStateException;
//import tpe.exceptions.roboter.exceptions.RobotMagicValueException;

/**
 * Das Interface repräsentiert den Befehlssatz eines einfachen Roboters.
 *
 * Jeder Roboter kann zwei grundlegende Operationen durchführen: das Umwandeln
 * einer Menge von Zahlen in einen String (<code>speak(...)</code>) und das
 * sortieren eines Arrays von Zahlen (<code>think(...)</code>). Wie genau das
 * Sortieren oder die Umwandlung erfolgt, hängt vom jeweiligen Typ des Roboters ab.
 *
 * Zu beachten ist, dass die Methoden dieses Interfaces nur auf Robotern benutzt
 * werden können, die eingeschaltet sind. Versucht man sie auf einem
 * ausgeschalteten Roboter zu benutzen, werfen sie eine {@link RobotIllegalStateException}.
 *
 * Weiterhin haben alle Roboter einen kleinen technischen Defekt, der dazu führt
 * dass die Methoden dieses Interfaces abstürzen, wenn in den Eingabedaten ein
 * spezieller Wert vorkommt. Immer wenn (<code>speak(...)</code>) oder (
 * <code>think(...)</code>) mit einem Array aufgerufen werden, das irgendwo die
 * Zahl {@literal 42} enthält, verweigern sie ihren Dienst und werfen eine
 * {@link RobotMagicValueException}.
 */
public interface RobotInstructions {

    /**
     * Gibt ein Array von Zahlen als String zurück. Die Zahlen werden
     * <b>nicht</b> sortiert.
     *
     * @param zahlen Zahlen, die ausgegeben werden sollen.
     * @return Zahlen als String
     * @throws RobotException wenn der Roboter in einem ungültigen Zustand ist,
     *             oder das Array nicht seinen Vorstellungen entspricht.
     */
    public String speak(int[] zahlen) throws RobotException;

    /**
     * Sortiert ein Array von Zahlen. Die Reihenfolge hängt von dem Typ des
     * Roboters ab.
     *
     * @param zahlen Zahlen, die sortiert werden sollen.
     * @return Sortierte Zahlen
     * @throws RobotException wenn der Roboter in einem ungültigen Zustand ist,
     *             oder das Array nicht seinen Vorstellungen entspricht.
     */
    public int[] think(int[] zahlen) throws RobotException;
}