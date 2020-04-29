import java.util.Scanner;

public class EinfacherRechner {

    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);

        /*
            Unterschied zwischen Kopf- und Fußgesteuerter Schleife?
         */
        do {
            /*
                Eingaben möglist einfach gestalten, kein Fancy-Zeug einbauen
                Fancy macht immer arbeit, ist oft wartungsintensiv und verursacht Fehler
                Also nur einbauen was wirklich nötig ist.

                An dieser Stelle habe ich print() anstelle von println() verwendet, damit
                die Eingabe direkt hinter dem Doppelpunkt stattfindet. println() gibt
                automatisch eine Leerzeile am Ende aus.
             */
            System.out.print("Operatorauswahl (+, -, *, /, everything else=exit): ");
            String operator = input.next();

            /*
                Love it ;-)
                return beendet das Programm - auch aus der Endlos-Schleife. Damit spart
                man sich aufwändige Prüfbedingungen im Schleifenfuß oder -kopf
             */
            //inklusiver Stop
            // if (operator.equals("e")) return;

            //exklusiver Stop
            if ("+-*/".indexOf(operator) == -1) return;

            /*
                Alternative Möglichkeit: Endlos-Schleife mit Fehlermeldung "ungültige Auswahl"
                solange keine Auswahl aus dem Menü getroffen wurde. Eine Frage der Anforderung.
                do {
                    ...
                } while (eingabe ungültig);
             */

            System.out.print("x: ");
            Double x = input.nextDouble();

            System.out.print("y: ");
            Double y = input.nextDouble();

            Double result = 0.0;

            /*
            Die Division-Methode wirft einen Fehler, wenn y=0 - um diesen Abzufangen
            verwendet man einen Try-Catch-Block. Genauso richtig ist es, in der Methode
            auf y == 0 zu prüfen und einen Standard-Wert zurückzugeben. Alles eine Frage
            wie die Anforderung definiert ist.
             */
            try {
                /*
                Switch-Case möglist Schlank. Viel weniger geht nicht mehr. Max 1-2 Anweisungen (ohne break)
                 */
                switch (operator) {
                    case "+":
                        result = add(x, y);
                        break;
                    case "-":
                        result = sub(x, y);
                        break;
                    case "*":
                        result = mul(x, y);
                        break;
                    case "/":
                        result = div(x, y);
                        break;
                }
            }
            catch (ArithmeticException ex){
                /*
                Der Catch-Block fängt die Exception ab und gibt eine "saubere" Fehlermeldung aus.
                Bei catch() hätte man anstatt der "ArithmeticException" auch nur "Exception" angeben
                können. Dann fängt der catch-Block jeden Exception-Typ ab - dazu später mehr.

                Die Ausgabe erfolgt auf System.err, statt System.out
                 */
                System.err.println(ex.getMessage());
            }

            /*
                Formatierte Ausgabe des Ergebnisses  %f = Platzhalter für Floating-Point-Variablen
                10.2 heißt: 10 Stellen vor dem Komma, 2 Nachkommastellen.

                https://www.baeldung.com/java-printstream-printf    <-- generell sehr gute Seite!
             */

            System.out.printf("Ergebnis: %10.2f \n\n", result);

        } while(true);
    }

    /*
        Weißt du warum alle Methoden in der Main-Klasse den static-Modifikator haben müssen?
        https://javabeginners.de/Grundlagen/Modifikatoren/static.php
     */
    private static Double add(Double x, Double y)
    {
        return x + y;
    }

    private static Double sub(Double x, Double y)
    {
        return x - y;
    }

    private static Double mul(Double x, Double y)
    {
        return x * y;
    }

    /*
        Wenn die Methode selbst eine Exception wirft, muss dies der Methoden-Signatur
        bekannt gemacht werden, sonst gibt es einen "unhandled exception error"
     */
    private static Double div(Double x, Double y) throws ArithmeticException
    {
        /*
            Fehlerbedingung, löst eine ArithmeticException aus
         */
        if (y == 0.0){
            throw new ArithmeticException("Error: division by zero. y cannot be 0!");
        }

        return x / y;
    }

    /*
        Auch eine von vielen möglichen Varianten
     */
    private static Double div2(Double x, Double y)
    {
        if (y == 0.0) {
            return 0.0;
        }

        return x / y;
    }
}
