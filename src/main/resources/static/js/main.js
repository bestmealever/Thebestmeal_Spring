let categoryWant;
let emotionWant;
let yesterdayEat;

$.ajaxPrefilter(function (options, originalOptions, jqXHR) {
    if (localStorage.getItem('token')) {
        jqXHR.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
    }
})

let foodObjArray;
let foodObjArrayNum;
let foodName;

function want() {
    let btn_val = []
    for (let i = 0; i < 9; i++) {
        let btnCheck = $(`input:checkbox[id="chk${i + 1}"]`)
        if (btnCheck.is(":checked") === true) {
            btn_val.push(btnCheck.val())
        }
    }
    if (btn_val == '') {
        alert('하나 이상 선택해주세요!');
    } else {
        console.log(btn_val)
        categoryWant = btn_val
        let temp_html = `<div class="question-h">
                                            <p class="question-style"> Q.2 오늘 기분은 어때요? </p>
                                        </div>
                                        <div class="button-group-in">
                                            <input type="checkbox" id="chk1" class="select-category" value="no_time">
                                            <label class="category" for="chk1">시간이 없어요</label>
                                            <input type="checkbox" id="chk2" class="select-category" value="many_time">
                                            <label class="category" for="chk2">시간 많아요!</label>
                                            <input type="checkbox" id="chk3" class="select-category" value="perfect">
                                            <label class="category" for="chk3">완벽한 저녁이 필요해요!</label>
                                        </div>
                                        <div class="button-group-in">
                                            <input type="checkbox" id="chk4" class="select-category" value="needsugar">
                                            <label class="category" for="chk4">당 떨어져요 ㅠ</label>
                                            <input type="checkbox" id="chk5" class="select-category" value="stressed">
                                            <label class="category" for="chk5">스트레스 받았어요</label>
                                            <input type="checkbox" id="chk6" class="select-category" value="fatty">
                                            <label class="category" for="chk6">기름진게 땡기네요!</label>
                                        </div>
                                        <div class="button-group-out">
                                            <button class="button next-stage" onclick="feeling()">다음</button>
                                            <button class="button next-stage" onclick="feeling_no()">잘 모르겠어요</button>
                                        </div>`
        let btnGroup = $('#button-group')
        btnGroup.empty();
        btnGroup.append(temp_html);
    }
}

function want_no() {
    console.log('want_no');
    let temp_html = `        <div class="question-h">
                                        <p class="question-style"> Q.2 그럼 어제는 뭐 먹었어요? </p>
                                    </div>
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
                                    <div class="button-group-out">
                                        <button class="button next-stage" onclick="yesterday()">다음</button>
                                        <button class="button next-stage" onclick="yesterday_no()">잘 모르겠어요</button>
                                    </div>`
    let btnGroup = $('#button-group')
    btnGroup.empty()
    btnGroup.append(temp_html)
}

function yesterday() {
    let btn_val = []
    for (let i = 0; i < 9; i++) {
        let btnCheck = $(`input:checkbox[id="chk${i + 1}"]`)
        if (btnCheck.is(":checked") === true) {
            btn_val.push(btnCheck.val())
        }
    }
    if (btn_val == '') {
        alert('하나 이상 선택해주세요!');
    } else {
        console.log(btn_val)
        yesterdayEat = btn_val
        let temp_html = `<div class="question-h">
                                            <p class="question-style"> Q.3 오늘 기분은 어때요? </p>
                                        </div>
                                        <div class="button-group-in">
                                            <input type="checkbox" id="chk1" class="select-category" value="no_time">
                                            <label class="category" for="chk1">시간이 없어요</label>
                                            <input type="checkbox" id="chk2" class="select-category" value="many_time">
                                            <label class="category" for="chk2">시간 많아요!</label>
                                            <input type="checkbox" id="chk3" class="select-category" value="perfect">
                                            <label class="category" for="chk3">완벽한 저녁이 필요해요!</label>
                                        </div>
                                        <div class="button-group-in">
                                            <input type="checkbox" id="chk4" class="select-category" value="needsugar">
                                            <label class="category" for="chk4">당 떨어져요 ㅠ</label>
                                            <input type="checkbox" id="chk5" class="select-category" value="stressed">
                                            <label class="category" for="chk5">스트레스 받았어요</label>
                                            <input type="checkbox" id="chk6" class="select-category" value="fatty">
                                            <label class="category" for="chk6">기름진게 땡기네요!</label>
                                        </div>
                                        <div class="button-group-out">
                                            <button class="button next-stage" onclick="feeling()">다음</button>
                                            <button class="button next-stage" onclick="feeling_no()">잘 모르겠어요</button>
                                        </div>`
        let btnGroup = $('#button-group')
        btnGroup.empty()
        btnGroup.append(temp_html)
    }
}

function yesterday_no() {
    console.log('yesterday_no');
    let temp_html = `<div class="question-h">
                                <p class="question-style"> Q.3 오늘 기분은 어때요? </p>
                            </div>
                            <div class="button-group-in">
                                    <input type="checkbox" id="chk1" class="select-category" value="no_time">
                                    <label class="category" for="chk1">시간이 없어요</label>
                                    <input type="checkbox" id="chk2" class="select-category" value="many_time">
                                    <label class="category" for="chk2">시간 많아요!</label>
                                    <input type="checkbox" id="chk3" class="select-category" value="perfect">
                                    <label class="category" for="chk3">완벽한 저녁이 필요해요!</label>
                                </div>
                                <div class="button-group-in">
                                    <input type="checkbox" id="chk4" class="select-category" value="needsugar">
                                    <label class="category" for="chk4">당 떨어져요 ㅠ</label>
                                    <input type="checkbox" id="chk5" class="select-category" value="stressed">
                                    <label class="category" for="chk5">스트레스 받았어요</label>
                                    <input type="checkbox" id="chk6" class="select-category" value="fatty">
                                    <label class="category" for="chk6">기름진게 땡기네요!</label>
                                </div>
                                <div class="button-group-out">
                                    <button class="button next-stage" onclick="feeling()">다음</button>
                                    <button class="button next-stage" onclick="feeling_no()">잘 모르겠어요</button>
                                </div>`
    let btnGroup = $('#button-group')
    btnGroup.empty()
    btnGroup.append(temp_html)

}

//category, emotion 선택
function feeling() {
    let btn_val = []
    for (let i = 0; i < 9; i++) {
        let btnCheck = $(`input:checkbox[id="chk${i + 1}"]`)
        if (btnCheck.is(":checked") === true) {
            btn_val.push(btnCheck.val())
        }
    }
    if (btn_val == '') {
        alert('하나 이상 선택해주세요!')
    } else {
        emotionWant = btn_val
        let data = {"categoryWant": categoryWant, "emotionWant": emotionWant, "yesterdayEat": yesterdayEat};
        console.log(data)
        $.ajax({
            type: "POST",
            url: `${apiUrl}/api/recommend`,
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            beforeSend: function () {
                //통신을 시작할때 처리되는 함수
                $('html').css("cursor", "wait");   // 현재 html 문서위에 있는 마우스 커서를 로딩 중 커서로 변경
            },
            complete: function () {
                //통신이 완료된 후 처리되는 함수
                $('html').css("cursor", "auto"); // 통신이 완료 된 후 다시 일반적인 커서 모양으로 변경
            },
            success: function (response) {
                console.log("도당체 뭐지")
                console.log(typeof response)
                if (response === '') {
                    console.log('???')
                    alert('추천할 만한 음식이 없네요 ㅠㅠ')
                } else {
                    foodObjArray = response
                    foodObjArrayNum = 0
                    selectFoodOnClient()
                }
            }
        })
    }
}

function feeling_no() {
    // alert('여기도 수정해야 됨...')
}

function selectFoodOnClient(foodObjArrayNum = 0) {

    if (foodObjArrayNum < 0) {
        foodObjArrayNum = foodObjArray.length - 1
    } else if (foodObjArrayNum >= foodObjArray.length) {
        foodObjArrayNum = 0
    }
    foodName = foodObjArray[foodObjArrayNum]['name']
    let temp_html = `<div class="question-h">
                        <p class="todays"> 오늘은, <span id="recommend">${foodObjArray[foodObjArrayNum]['name']}</span> 어때요?!
                        </p>
                    </div>
                    <div class="mealimg" style="background-image: url('${foodObjArray[foodObjArrayNum]['imageUrl']}"
                         alt="${foodObjArray[foodObjArrayNum]['name']}"></div>
                    <div class="button-group-out">
                        <button class="button next-stage" onclick="selectFoodOnClient(${foodObjArrayNum - 1})">이전 추천 음식</button>
                        <button class="button next-stage" onclick="viewKakao(foodName)">이거 먹을게요!</button>
                        <button class="button next-stage" onclick="selectFoodOnClient(${foodObjArrayNum + 1})">다음 추천 음식</button>
                    </div>`
    let btnGroup = $('#button-group')
    btnGroup.empty()
    btnGroup.append(temp_html)
}

// 카카오 페이지로 이동
function viewKakao(foodName) {
    console.log('TT')
    $.ajax({
        type: "GET",
        url: `${apiUrl}/api/recommended?foodName=${foodName}`,
        success: function (response) {
            console.log(response)
            window.location.href = `kakao.html?foodName=${foodName}`
        }
    })
}

function Tag(foodList) {
    for (let i = 0; i < foodList['tags'].length; i++) {
        let tags = foodList['tags'][i]
        let tag = tags['tagName']
        if (tag === "korean") {
            let tag_korean = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">한식</span>`
            $(`#tags${foodList['id']}`).append(tag_korean);
        } else if (tag === "western") {
            let tag_western = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">양식</span>`
            $(`#tags${foodList['id']}`).append(tag_western);
        } else if (tag === "chinese") {
            let tag_chinese = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">중식</span>`
            $(`#tags${foodList['id']}`).append(tag_chinese);
        } else if (tag === "japanese") {
            let tag_japanese = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">일식</span>`
            $(`#tags${foodList['id']}`).append(tag_japanese);
        } else if (tag === "supper") {
            let tag_supper = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">야식</span>`
            $(`#tags${foodList['id']}`).append(tag_supper);
        } else if (tag === "snack") {
            let tag_snack = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">분식</span>`
            $(`#tags${foodList['id']}`).append(tag_snack);
        } else if (tag === "fastfood") {
            let tag_fastfood = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">패스트푸드</span>`
            $(`#tags${foodList['id']}`).append(tag_fastfood);
        } else if (tag === "salad") {
            let tag_salad = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">샐러드</span>`
            $(`#tags${foodList['id']}`).append(tag_salad);
        } else if (tag === "many_time") {
            let tag_manytime = `<span class="badge text-dark" style="background-color: lightgray;margin-left:5px;">시간 많을 때</span>`
            $(`#tags${foodList['id']}`).append(tag_manytime);
        } else if (tag === "perfect") {
            let tag_perfect = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">완벽한 식사</span>`
            $(`#tags${foodList['id']}`).append(tag_perfect);
        } else if (tag === "stressed") {
            let tag_stressed = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">스트레스 받을 때</span>`
            $(`#tags${foodList['id']}`).append(tag_stressed);
        } else if (tag === "fatty") {
            let tag_fatty = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">기름 진거 땡길 때</span>`
            $(`#tags${foodList['id']}`).append(tag_fatty);
        } else if (tag === "no_time") {
            let tag_notime = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">시간 없을 떄</span>`
            $(`#tags${foodList['id']}`).append(tag_notime);
        } else if (tag === "needsugar") {
            let tag_needsugar = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">당 땡겨</span>`
            $(`#tags${foodList['id']}`).append(tag_needsugar);
        }
    }
}

function heart(id) {
    if ($(`#heart${id}`).hasClass("bi bi-heart")) {
        $(`#heart${id}`).removeClass("bi-heart").addClass("bi-heart-fill");
        console.log(id);
        $.ajax({
            type: "POST",
            url: `${apiUrl}/liked/${id}`,
            success: function (response) {
                console.log(response);
                window.location.reload();
            }
        })
    } else {
        $(`#heart${id}`).removeClass("bi-heart-fill").addClass("bi-heart");
        console.log(id);
        $.ajax({
            type: "DELETE",
            url: `${apiUrl}/liked/${id}`,
            success: function (response) {
                console.log(response);
                window.location.reload();
            }
        })
    }
}