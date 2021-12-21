let user;
let foodrecommends;
let recommendedList;
let postings;


$(document).ready(function () {
    $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
        if (localStorage.getItem('token')) {
            jqXHR.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
        }
    });
    $("#logout").click(function () {
        //로그아웃
        localStorage.removeItem("token");
        localStorage.removeItem("username");
        localStorage.removeItem("nickname");
        Object.keys(localStorage)
            .filter(key => key.startsWith('kakao_'))
            .forEach(key => localStorage.removeItem(key));
        // Object.keys(localStorage).filter(key => localStorage.getItem(key).startsWith('kakao_')).forEach(key => localStorage.removeItem(key));

        location.href = '/';
    });

    //(카카오 로그인) 유무에 따라 OO님 입력값 변경. 카카오: nickname / 일반: username
    if (localStorage.getItem("nickname")) {
        $("#welcomename").html(localStorage.getItem("nickname"));
    }  else {
        $("#welcomename").html(localStorage.getItem("username"));
    }

    //마이페이지 처음 출력 내가 추천받은 음식
    $.ajax({
        type: "GET",
        url: `${apiUrl}/mypage`,
        success: function (response) {
            console.log(response)
            user = response['user']
            foodrecommends = response['foods']
            recommendedList = response['recommendedList']
            postings = response['postings']

            $("#username").html(user['username']);
            $('#usercomment').html(user['statusMessage']);
            let profile_pic = user['profilePhoto'];
            let temp_img = `
                    <img style="width:70px; height:70px; border-radius: 70px;  object-fit: cover; float:left; " src="${profile_pic}" >`
            // $('#profile_pic').attr("src", "/css/food_sample.jpg");
            $('#profile_pic').append(temp_img);
            nav1()
        }
    })
})

//내가 추천받은 음식 클릭시
function nav1() {
    console.log(foodrecommends, "nav1에 뿌려질 정보");
    $('#user-table').empty();
    $('#user-nav').empty();
    let temp_nav = `<div class="mypage-nav" style=" clear:left; margin-top: 30px;">
                        <a href="#" style="text-decoration: none;  border-bottom: 2px solid; padding-bottom: 10px; color:black; margin-right: 12px; margin-left: 10px;"><strong>내가 추천받은 음식</strong></a>
                        <a href="#" onclick="nav2();" style="text-decoration: none;color:black; margin-right: 12px; margin-left: 10px;"><strong>내가 추천한 음식</strong></a>
                        <a href="#" onclick="nav3();" style="text-decoration: none;color:black; margin-right: 12px; margin-left: 10px;"><strong>내가 남긴 댓글</strong></a>
                        <hr style="margin-top:8px;">
                    </div>
                    <div id="user-nav"></div>`
    $('#user-table').append(temp_nav);
    $('#foodList').empty();
    for (let i = 0; i < foodrecommends.length; i++) {
        let foodList = foodrecommends[i]
        let recommended = recommendedList[i]
        console.log(foodList);
        console.log(foodList['id']);
        let temp = `<div id="${foodList['id']}"  class="foodListCard">
                        <div class="foodImage" style='background-image:url("${foodList['imageUrl']}")'></div>
                        <div class="foodInfo">
                            <p class="foodListCardName">${foodList['name']}</p>
                            <i class="bi bi-heart" id="heart" onclick=""></i><span class="likedLength">${foodList['likedFood'].length}</span>
                            <p class="foodListCardTags" id="tags${recommended['id']}"></p>
                            <p>선택 일자 : ${recommended['createdAt'].substr(0, 10)}</p>
                        </div>
                    </div>`
        $('#foodList').append(temp);
        Tag_mypage(foodList, recommended['id']);
    }
}


function nav2() {
    console.log(postings, "nav2에 뿌려질 정보");
    $('#user-table').empty();
    $('#user-nav').empty();
    let temp_nav = `<div class="mypage-nav" style=" clear:left; margin-top: 30px;">
                        <a href="#" onclick="nav1();" style="text-decoration: none;color:black; margin-right: 12px; margin-left: 10px;"><strong>내가 추천받은 음식</strong></a>
                        <a href="#" style="text-decoration: none;  border-bottom: 2px solid; padding-bottom: 10px; color:black; margin-right: 12px; margin-left: 10px;"><strong>내가 추천한 음식</strong></a>
                        <a href="#" onclick="nav3();" style="text-decoration: none;color:black; margin-right: 12px; margin-left: 10px;"><strong>내가 남긴 댓글</strong></a>
                        <hr style="margin-top:8px;">
                    </div>
                    <div id="user-nav"></div>`
    $('#user-table').append(temp_nav);
    $('#foodList').empty();
    for (let i = 0; i < postings.length; i++) {
        let postingList = postings[i]['food']
        console.log(postingList);
        console.log(postingList['id']);
        let temp = `<div id="${postingList['id']}"  class="foodListCard">
                        <div class="foodImage" style='background-image:url("${postingList['imageUrl']}")'></div>
                        <div class="foodInfo">
                            <p class="foodListCardName">${postingList['name']}</p>
                            <i class="bi bi-heart" id="heart" onclick=""></i><span class="likedLength">${postingList['likedFood'].length}</span>
                            <p class="foodListCardTags" id="tags${postingList['id']}"></p>
                        </div>
                    </div>`
        $('#foodList').append(temp);
        Tag(postingList);
    }
}

function nav3() {
    console.log(postings, "nav3에 뿌려질 정보");
    $('#user-table').empty();
    $('#user-nav').empty();
    let temp_nav = `<div class="mypage-nav" style=" clear:left; margin-top: 30px;">
                        <a href="#" onclick="nav1();" style="text-decoration: none;color:black; margin-right: 12px; margin-left: 10px;"><strong>내가 추천받은 음식</strong></a>
                        <a href="#" onclick="nav2();" style="text-decoration: none;color:black; margin-right: 12px; margin-left: 10px;"><strong>내가 추천한 음식</strong></a>
                        <a href="#" style="text-decoration: none;  border-bottom: 2px solid; padding-bottom: 10px; color:black; margin-right: 12px; margin-left: 10px;"><strong>내가 남긴 댓글</strong></a>
                        <hr style="margin-top:8px;">
                    </div>
                    <div id="user-nav"></div>`
    $('#user-table').append(temp_nav);
    $('#foodList').empty();
    for (let i = 0; i < postings.length; i++) {
        let postingList = postings[i]
        console.log(postingList);
        console.log(postingList['id']);
        let temp = `<div id="${postingList['id']}"  class="foodListCard">
                        <div class="foodImage" style='background-image:url("${postingList['food']['imageUrl']}")'></div>
                        <div class="foodInfo">
                            <p class="foodListCardName">${postingList['food']['name']}</p>
                            <p>${postingList['postingMemo']}</p>
                        </div>
                    </div>`
        $('#foodList').append(temp);
    }
}

function Tag_mypage(foodList, recommended_id) {
    for (let i = 0; i < foodList['tags'].length; i++) {
        let tags = foodList['tags'][i]
        let tag = tags['tagName']
        if (tag === "korean") {
            let tag_korean = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">한식</span>`
            $(`#tags${recommended_id}`).append(tag_korean);
        } else if (tag === "western") {
            let tag_western = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">양식</span>`
            $(`#tags${recommended_id}`).append(tag_western);
        } else if (tag === "chinese") {
            let tag_chinese = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">중식</span>`
            $(`#tags${recommended_id}`).append(tag_chinese);
        } else if (tag === "japanese") {
            let tag_japanese = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">일식</span>`
            $(`#tags${recommended_id}`).append(tag_japanese);
        } else if (tag === "supper") {
            let tag_supper = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">야식</span>`
            $(`#tags${recommended_id}`).append(tag_supper);
        } else if (tag === "snack") {
            let tag_snack = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">분식</span>`
            $(`#tags${recommended_id}`).append(tag_snack);
        } else if (tag === "fastfood") {
            let tag_fastfood = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">패스트푸드</span>`
            $(`#tags${recommended_id}`).append(tag_fastfood);
        } else if (tag === "salad") {
            let tag_salad = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">샐러드</span>`
            $(`#tags${recommended_id}`).append(tag_salad);
        } else if (tag === "many_time") {
            let tag_manytime = `<span class="badge text-dark" style="background-color: lightgray;margin-left:5px;">시간 많을 때</span>`
            $(`#tags${recommended_id}`).append(tag_manytime);
        } else if (tag === "perfect") {
            let tag_perfect = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">완벽한 식사</span>`
            $(`#tags${recommended_id}`).append(tag_perfect);
        } else if (tag === "stressed") {
            let tag_stressed = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">스트레스 받을 때</span>`
            $(`#tags${recommended_id}`).append(tag_stressed);
        } else if (tag === "fatty") {
            let tag_fatty = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">기름 진거 땡길 때</span>`
            $(`#tags${recommended_id}`).append(tag_fatty);
        } else if (tag === "no_time") {
            let tag_notime = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">시간 없을 떄</span>`
            $(`#tags${recommended_id}`).append(tag_notime);
        } else if (tag === "needsugar") {
            let tag_needsugar = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">당 땡겨</span>`
            $(`#tags${recommended_id}`).append(tag_needsugar);
        }
    }
}

function profilePicUpload() {
    let images = new FormData($('#images')[0]);
    // console.log($('#images')[0][0].files[0])
    $.ajax({
        type: "POST",
        url: `${apiUrl}/images`,
        processData: false,
        contentType: false,
        data: images,
        success: function (response) {
            alert("업로드 성공");
            window.location.reload();
        },
        error : function (request) {
            if(request.status === 500) {
                alert("사진 용량 1MB 이하, jpg, jpeg, png 형식의 사진만 업로드 가능합니다.")
            } else (alert("에러!"))
        }
    })
}

function statusMessageModify() {
    $.ajax({
        type: "GET",
        url: `${apiUrl}/mypage`,
        success: function (response) {
            console.log(response);
            let nowmsg = response['statusMessage']
            $("#usercomment").empty()
            let input = `
                                <input type = "text" id = "statusMessage" placeholder="${nowmsg}"/>
                                <button onclick="statusMessageSave()">저장</button>
                                <button onclick="statusMessageCancel()">취소</button>
                                `
            $("#usercomment").append(input)
        }
    })
}

function statusMessageSave() {
    let statusMessage = {
        "statusMessage": $("#statusMessage").val()
    }
    $.ajax({
        type: "PUT",
        url: `${apiUrl}/mypage/statusMessage`,
        contentType: "application/json",
        data: JSON.stringify(statusMessage),
        success: function (response) {
            alert("저장되었습니다!")
            window.location.reload();
        }
    })
}

function statusMessageCancel() {
    $.ajax({
        type: "GET",
        url: `${apiUrl}/mypage`,
        success: function (response) {
            console.log(response);
            $("#usercomment").empty()
            $('#usercomment').html(response['statusMessage']);
        }
    })
}
