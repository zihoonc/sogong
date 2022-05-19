var main = {
    init: function () {
        var _this = this;
        $('#btn-update').on('click', function () {
            _this.update();
        });
    },
    update: function () {
        var data = {
            arrive: $('#arrive').val()
        };

        var bookingId = $('#bookingId').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/books/' + bookingId,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error))
        });
    }
};

main.init();