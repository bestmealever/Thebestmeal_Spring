let categoryWant;
let emotionWant;
let yesterdayEat;


function want() {
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
        categoryWant = btn_val
        let temp_html = `<div class="question-h">
                                        <p class="question-style"> Q.2 어제는 뭐 먹었어요? </p>
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
        alert('선택바람');
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
        alert('선택바람')
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
                alert("통신성공!")
                console.log(response)
                let temp_html = `
                                이 음식 어떠세요?
                                추천하는 음식
                                음식 image url
                                `
                let btnGroup = $('#button-group')
                btnGroup.empty()
                btnGroup.append(temp_html)
            }
        })

    }
}

function feeling_no() {
    alert('선택할게 없어요 하나이상 선태해주세요')
}

function retry() {
    alert('공사중')
}

