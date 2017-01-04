var table;
var toDelete = [];
var commited = true;
function administration(sitename) {

    $(document).ready(function () {
        table = $('#resultTable').DataTable({
            "order": [[0, "asc"]],
            "language": {
                "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/German.json"
            }
        });
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

        function generataUrlString(PKsToDelete) {
            var urlString = "";
            for (var i = 0; i < PKsToDelete.length; i++) {
                urlString += PKsToDelete[i] + "=";
            }
            urlString = urlString.substring(0, urlString.length - 1);
            return urlString;
        }

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
        window.onbeforeunload = function () {
            if (!commited)
                return 'Wollen Sie die vorgenommenen Änderungen beibehalten?';
        }
    });
}
function remove(PKToRemove) {
    table.row('.selected').remove().draw(false);
    toDelete.push(PKToRemove);
    setUncommitedStyle();
}
;

function setUncommitedStyle() {
    document.getElementById("commit").style.background = "#F79E9E";
    commited = false;
}