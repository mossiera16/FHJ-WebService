/*JavaScript-Datei zur administrativen Verwaltung von Kurs-, Vortragenden- und Studentendaten
 * 
 * 
 * 
 */
var table;
var toDelete = [];
var commited = true;
function administration(sitename) {

    /*
     * Wenn das HTML-Dokument fertig geladen ist, können erst diese Funktionen ausgeführt werden.
     */
    $(document).ready(function () {
        /*
         * Initialisierung der Datentabelle für Sortierung, Filterung und ästhetische Darstellung der Informationen
         */
        table = $('#resultTable').DataTable({
            "order": [[0, "asc"]],
            "language": {
                "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/German.json"
            }
        });
        /*
         * Falls auf den Button mit der ID: 'commit' geklickt wird, wird diese Funktion aufgerufen
         */
        $('#commit').click(function () {
            var data = table.$('input, select').serialize();
            var urlString = generataUrlString(toDelete);
            
            if (urlString === "") {
                if (data !== "") {
                    urlString = "?insert-update=" + data;
                }
            } else {
                urlString = "?delete=" + urlString + "&insert-update=" + data;
            }
            
            document.getElementById("commit").style.background = "#A7D177";
            commited = true;
            window.location.replace(sitename + urlString);
            
        });

        /*
         * In dieser Funktion wird ein Teil des URL-Strings generiert.
         * Die übergebenen Primärschlüssel der zu löschenden Datensätze wird übergeben.
         */
        function generataUrlString(PKsToDelete) {
            var urlString = "";
            for (var i = 0; i < PKsToDelete.length; i++) {
                urlString += PKsToDelete[i] + "=";
            }
            urlString = urlString.substring(0, urlString.length - 1);
            return urlString;
        }

        /*
         * Falls auf den Button mit der ID: 'add' geklickt wird, wird diese Funktion aufgerufen
         * Hierbei wird die letzte Zeile geklont und eine Zeile darunter eingefügt.
         */
        $('#add').on('click', function () {
            var myTr = $(this).closest('tr');
            var clone = myTr.clone();
            table.row.add(clone);
            myTr.after(clone);
            table.destroy();
            table = $('#resultTable').DataTable({
                "order": [[0, "asc"]],
                "language": {
                    "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/German.json"
                }});
            setUncommitedStyle();
        });
        $('#resultTable tbody').on('click', 'tr', function () {
            if ($(this).hasClass('selected')) {
                $(this).removeClass('selected');
            } else {
                table.$('tr.selected').removeClass('selected');
                $(this).addClass('selected');
            }
        });
        /*
         * Falls noch nicht vorgenommene Änderungen existieren, wird abgefragt ob der Benutzer die Seite ohne Speicherung der Änderungen verlassen möchte
         */
        window.onbeforeunload = function () {
            if (!commited)
                return 'Wollen Sie die vorgenommenen Änderungen beibehalten?';
        }
    });
}
/*
 * Funktion für das Entfernen (Zeile wird ausgeblendet und PK wird in ein Array gespeichert) 
 */
function remove(PKToRemove) {
    document.getElementById(PKToRemove).style.display = "none";
    //table.row('.selected').remove().draw(false);
    toDelete.push(PKToRemove);
    setUncommitedStyle();
}
;

/*
 * Funktion zur Setzung des Styles, falls noch nicht gespeicherte Änderungen existieren.
 */
function setUncommitedStyle() {
    document.getElementById("commit").style.background = "#F79E9E";
    commited = false;
}