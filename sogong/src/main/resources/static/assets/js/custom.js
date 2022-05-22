// to get current year
function getYear() {
    var currentDate = new Date();
    var currentYear = currentDate.getFullYear();
    document.querySelector("#displayYear").innerHTML = currentYear;
}

getYear();


// isotope js
$(window).on('load', function () {
    $('.filters_menu li').click(function () {
        $('.filters_menu li').removeClass('active');
        $(this).addClass('active');

        var data = $(this).attr('data-filter');
        $grid.isotope({
            filter: data
        })
    });

    var $grid = $(".grid").isotope({
        itemSelector: ".all",
        percentPosition: false,
        masonry: {
            columnWidth: ".all"
        }
    })
});

// nice select
$(document).ready(function() {
    $('select').niceSelect();
  });

/** google_map js **/
function myMap() {
    var mapProp = {
        center: new google.maps.LatLng(40.712775, -74.005973),
        zoom: 18,
    };
    var map = new google.maps.Map(document.getElementById("googleMap"), mapProp);
}

// client section owl carousel
$(".client_owl-carousel").owlCarousel({
    loop: true,
    margin: 0,
    dots: false,
    nav: true,
    navText: [],
    autoplay: true,
    autoplayHoverPause: true,
    navText: [
        '<i class="fa fa-angle-left" aria-hidden="true"></i>',
        '<i class="fa fa-angle-right" aria-hidden="true"></i>'
    ],
    responsive: {
        0: {
            items: 1
        },
        768: {
            items: 2
        },
        1000: {
            items: 2
        }
    }
});

// select seat number
function selected(button) {
    document.getElementById('tableNumber').innerText = button.innerText;
}

// select book time & seat
function select() {
    document.getElementById('time').value = document.getElementById('timepicker').value
    document.getElementById('tableNum').value = document.getElementById('tableNumber').innerText
}




function getYmd() {
    //yyyy-mm-dd 포맷 날짜 생성
    let d = new Date();
    return d.getFullYear() + "-" + ((d.getMonth() + 1) > 9 ? (d.getMonth() + 1).toString() : "0" + (d.getMonth() + 1)) + "-" + (d.getDate() > 9 ? d.getDate().toString() : "0" + d.getDate().toString());
}
function checkForm() {
    if( !(document.getElementById('tableNum').value >=1 && document.getElementById('tableNum').value <=9)) {
        alert("테이블이 선택되지 않았습니다.");
        return false;
    }
    if( document.getElementById('people').value == '' ||  document.getElementById('people').value == 0) {
        alert("인원수를 입력해 주십시오.");
        return false;
    }
    let currentDate = getYmd();
    if(document.getElementById('date').value < currentDate){
        alert("정확한 날짜를 입력해주십시오.");
        return false;
    }
    bookinService.getBookList()
    return true;
}
