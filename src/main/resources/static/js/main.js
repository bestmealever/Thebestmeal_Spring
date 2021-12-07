let categoryWant;
let emotionWant;
let yesterdayEat;

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
            url: "/api/recommend",
            contentType: "application/json; charset=utf-8",
            data: JSON.stringify(data),
            success: function (response) {
                foodObjArray = response
                foodObjArrayNum = 0
                selectFoodOnClient()
            }
        })
    }
}

function feeling_no() {
    alert('여기도 수정해야 됨...')
}

function selectFoodOnClient(foodObjArrayNum = 0) {

    if (foodObjArrayNum < 0) {
        foodObjArrayNum = foodObjArray.length - 1
    } else if (foodObjArrayNum >= foodObjArray.length) {
        foodObjArrayNum = 0
    }
    foodName = foodObjArray[foodObjArrayNum]['name']
    let temp_html = `<div class="question-h">
                        <p class="todays">${foodObjArray[foodObjArrayNum]['name']} <span id="recommend">${foodObjArray[foodObjArrayNum]['name']}</span> ${foodObjArray[foodObjArrayNum]['name']}
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
    // console.log(foodName)
    // window.location.href = `/api/kakao?foodName=${foodName}`
    // window.location.href = `/api/kakao`
    window.location.href = `kakao.html?foodName=${foodName}`


    // $.ajax({
    //     type: "GET",
    //     url: "/api/kakao",
    //     // data: {foodName:foodName},
    //     success: function() {
    //     }
    // })
}
