let postingFoodName;
let postingCat;
let postingEmo;
let foodImg;
let postingMemo;


$(document).ready(function () {

    $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
        if (localStorage.getItem('token')) {
            jqXHR.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
        }

        $("#logout").click(function () {
            //로그아웃
            localStorage.removeItem("token");
            localStorage.removeItem("username");
            location.href = '/';
        });
    })
})

function step1() {

    let food = $("#foodname1").val();
    postingFoodName = food

    if (postingFoodName == "") {
        alert('선택바람')
        return;
    }
    //중복체크
    let data = {"postingFoodName": postingFoodName};

    $.ajax({
        type: "POST",
        url: "/api/foodcheck",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success: function (response) {
            if (response == true) {
                alert('앗, 이미 있는 음식이에요!')
                return;

            }
            //중복 없을 시 진행
            else {
                let temp_html = `<p class="question-style" style="margin-bottom: 10px;"> Q.2 어떤 종류의 음식인가요? </p>
                                         <div class="button-group-in">
                                                <input type="checkbox" id="chk1" class="select-category" value="korean">
                                                <label class="category" for="chk1">한식</label>
                                                <input type="checkbox" id="chk2" class="select-category" value="chinese">
                                                <label class="category" for="chk2">중식</label>
                                                <input type="checkbox" id="chk3" class="select-category" value="japanese">
                                                <label class="category" for="chk3">일식</label>
                                         </div>
                                         <div class="button-group-in">
                                                <input type="checkbox" id="chk4" class="select-category" value="western">
                                                <label class="category" for="chk4">양식</label>
                                                <input type="checkbox" id="chk5" class="select-category" value="snack">
                                                <label class="category" for="chk5">분식</label>
                                                <input type="checkbox" id="chk6" class="select-category" value="bread">
                                                <label class="category" for="chk6">빵</label>
                                         </div>
                                         <div class="button-group-in">
                                                <input type="checkbox" id="chk7" class="select-category" value="supper">
                                                <label class="category" for="chk7">야식</label>
                                                <input type="checkbox" id="chk8" class="select-category" value="fastfood">
                                                <label class="category" for="chk8">패스트푸드</label>
                                                <input type="checkbox" id="chk9" class="select-category" value="salad">
                                                <label class="category" for="chk9">샐러드</label>
                                         </div>
                                         <!--다음 버튼-->
                                          <div class="button-group-out">
                                             <button class="button next-stage" onclick="step2()">다음</button>
                                          </div>`
                let btnGroup = $('#button-group')
                btnGroup.empty();
                btnGroup.append(temp_html);
            }
        }
    })

}


function step2() {
    let btn_val = []
    for (let i = 0; i < 9; i++) {
        let btnCheck = $(`input:checkbox[id="chk${i + 1}"]`)
        if (btnCheck.is(":checked") === true) {
            btn_val.push(btnCheck.val())
        }
    }
    if (btn_val == '') {
        alert('선택바람');
    } else {
        console.log(btn_val)
        postingCat = btn_val
        let temp_html = `<p class="question-style" style="margin-bottom: 10px;"> Q.3 어떨 때 먹으면 좋아요? </p>
                                 <div class="button-group-in">
                                    <input type="checkbox" id="chk1" class="select-category" value="no_time">
                                    <label class="category" for="chk1">시간이 없을 때</label>
                                    <input type="checkbox" id="chk2" class="select-category" value="many_time">
                                    <label class="category" for="chk2">시간이 많을 때</label>
                                    <input type="checkbox" id="chk3" class="select-category" value="perfect">
                                    <label class="category" for="chk3">완벽한 저녁이 필요할 때</label>
                                 </div>
                                 <div class="button-group-in">
                                    <input type="checkbox" id="chk4" class="select-category" value="needsugar">
                                    <label class="category" for="chk4">당 떨어질 때</label>
                                    <input type="checkbox" id="chk5" class="select-category" value="stressed">
                                    <label class="category" for="chk5">스트레스 받을 때</label>
                                    <input type="checkbox" id="chk6" class="select-category" value="fatty">
                                    <label class="category" for="chk6">기름진게 땡길 때</label>
                                 </div>
                                 <!--다음 버튼-->
                                 <div class="button-group-out">
                                    <button class="button next-stage" onclick="step3()">다음</button>
                                 </div>`
        let btnGroup = $('#button-group')
        btnGroup.empty()
        btnGroup.append(temp_html)
    }
}

function step3() {
    let btn_val = []
    for (let i = 0; i < 9; i++) {
        let btnCheck = $(`input:checkbox[id="chk${i + 1}"]`)
        if (btnCheck.is(":checked") === true) {
            btn_val.push(btnCheck.val())
        }
    }
    if (btn_val == '') {
        alert('선택바람')
    } else {
        postingEmo = btn_val
        console.log(postingEmo)

        let temp_html = `<p class="question-style" style="margin-bottom: 10px;"> Q.4 음식 사진과 음식 소개를 부탁드려요! </p>
                                <div class = "form-group">
                  
                                            <input id="foodimages" type="file" name="avatar" />
                                            <!--코멘트-->
                                            <div style="width:450px;  float: left;">
                                                <input class="input is-rounded" style="text-align: center; height:200px; border-radius: 20px;  margin: 10px 0 0 0; word-wrap: break-word;" type="text" id="comment" placeholder="이 음식을 소개해주세요! (최대 45자까지 입력 가능합니다)" maxlength='45'>
                                            </div>
                                </div>
                                    

                                <div class="button-group-out">
                                    <button type="button" class="button next-stage" onclick="save()">저장</button>
                                </div>`

        let btnGroup = $('#button-group')
        btnGroup.empty()
        btnGroup.append(temp_html)
    }

}


function save() {
    postingMemo = $('#comment').val();
    foodImg = $('#foodimages')[0].files[0];

    let data = new FormData();
    data.append( "postingFoodName", postingFoodName);
    data.append( "postingCat", postingCat);
    data.append("postingEmo", postingEmo);
    data.append("foodImg", $('#foodimages')[0].files[0] );
    data.append( "postingMemo", postingMemo);

    console.log(data)

    $.ajax({
        type: "POST",
        url: "/api/post",
        processData: false,
        contentType: false,
        data: data,
        success: function (response) {
            let foodImgUrl = response
            console.log(foodImgUrl)
            alert("추천해주셔서 감사합니다!");
            let temp_html = `<p class="question-style" style="margin-bottom: 10px;">END. 음식 추천 완료! </p>
                            <div id="posting_result_img" style="background-image:url('${foodImgUrl}')"></div>
                            <div class="posting_result">
                                <p class="posting_title">당신의 추천 음식</p>
                                <p class="posting_foodname">${postingFoodName}</p>
                                <p class="posting_comment">${postingMemo}</p>
                           
                                <p>
                                <span class="tag is-warning posting_tag" style="background-color: #FED7BF;">${postingFoodName}</span>
                                <span class="tag is-warning posting_tag" style="background-color: #FED7BF;">존맛탱</span>
                                <span class="tag is-warning posting_tag" style="background-color: #FED7BF;">추천감사</span>
                                </p>
                            </div>
                            <div class="button-group-out">
                               <button class="button next-stage" onclick="window.location.href='/'">홈</button>
                            </div>
                            `
            let btnGroup = $('#button-group')
            btnGroup.empty()
            btnGroup.append(temp_html)
        }

    });
}