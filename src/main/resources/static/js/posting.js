//foodname -> postingfood
//
let postingFoodName;
let postingCat;
let postingEmo;
let foodImgUrl;
let postingMemo;


$(document).ready(function () {
    $.ajaxPrefilter(function (options, originalOptions, jqXHR) {
        if (localStorage.getItem('token')) {
            jqXHR.setRequestHeader('Authorization', 'Bearer ' + localStorage.getItem('token'));
        }
    });
    // $("#logout").click(function () {
    //     //로그아웃
    //     localStorage.removeItem("token");
    //     localStorage.removeItem("username");
    //     location.href = '/';
    // });
})


function step1() {
    let food = $("#foodname1").val(); {
        // 빈칸일 경우 경고
        if (food == '') {
            alert('선택바람')
        } else {
            console.log(food)
            postingFoodName = food
            let temp_html = `<p class="question-style" style="margin-bottom: 10px;"> Q.2 어떤 종류의 음식인가요? </p>
                                         <progress class="progress is-normal" value="25" max="100">25%</progress>
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
                                 <progress class="progress is-normal" value="50" max="100">50%</progress>
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
                                <progress class="progress is-normal" value="75" max="100">75%</progress>
                                <div class = "form-group">
<!--                                s3 전 임시로 넣어보기 url -->
                                            <input id="foodurl" placeholder="url">
                                            <!--코멘트-->
                                            <div style="width:450px;  float: left;">
                                                <input class="input is-rounded" style="text-align: center; height:200px; border-radius: 20px;  margin: 10px 0 0 0; word-wrap: break-word;" type="text" id="comment" placeholder="이 음식을 소개해주세요! (최대 45자까지 입력 가능합니다)" maxlength='45'>
                                            </div>
                                </div>
                                    
                                <!--파일 업로더-->
<!--                                <div class="form-group">-->
<!--                                    <div class="file is-small">-->
<!--                                        <form id="upload-file">-->
<!--                                            <label class="file-label" for="post-url"  name="file">-->
<!--                                                <input class="file-input" id="post-url" type="file"  name="file" accept="image/*" onchange="foodPicUpload;"/>-->
<!--                                                <span class="file-cta">-->
<!--                                               <span class="file-icon"><i class="fa fa-upload"></i></span><span class="file-label">음식 사진 내 컴퓨터에서 업로드</span></span>-->
<!--                                            </label>-->
<!--                                            &lt;!&ndash;이미지 미리보기&ndash;&gt;-->
<!--                                            <div id="image_container"></div>-->
<!--                                            &lt;!&ndash;코멘트&ndash;&gt;-->
<!--                                            <div style="width:450px;  float: left;">-->
<!--                                                <input class="input is-rounded" style="text-align: center; height:200px; border-radius: 20px;  margin: 10px 0 0 0; word-wrap: break-word;" type="text" id="comment" placeholder="이 음식을 소개해주세요! (최대 45자까지 입력 가능합니다)" maxlength='45'>-->
<!--                                            </div>-->
<!--                                        </form>-->
<!--                                    </div>-->
<!--                                </div>-->
                                <!--다음 버튼-->
                                <div class="button-group-out">
                                    <button type="button" class="button next-stage" onclick="save()">저장</button>
                                </div>`

                let btnGroup = $('#button-group')
                btnGroup.empty()
                btnGroup.append(temp_html)
            }

        }

        //아직 쓰질 못함..

// function foodPicUpload() {
//     let foodImgUrl = new FormData($('#images')[0]);
//     // console.log($('#images')[0][0].files[0])
//     $.ajax({
//         type:"POST",
//         url: "/images",
//         processData: false,
//         contentType: false,
//         data: images,
//         success: function(response){
//             alert("업로드 성공");
//             window.location.reload();
//         }
//     })
// }



function save() {
//category,emotion..
    foodImgUrl = $('#foodurl').val();
    postingMemo = $('#comment').val();

    let data = {
        "postingFoodName": postingFoodName,
        "postingCat": postingCat,
        "postingEmo": postingEmo,
        "foodImgUrl": foodImgUrl,
        "postingMemo":  postingMemo
    }

    console.log(data)

    $.ajax({
        type: "POST",
        url: "/api/post",
        contentType: "application/json; charset=utf-8",
        data: JSON.stringify(data),
        success: function (response) {
                alert("추천해주셔서 감사합니다!");
                window.location.reload();

        },
    });
 }

// 이전 파이썬 코드 + s3에 이미지 저장 + 미리보기 하는 코드 (추후 수정 필요)

// 이미지 미리보기 이벤트 이미지 업로드는 포스팅과 별도로 할지?

//맨 마지막 추천한 음식은 ~ 코드 작업 중 (내가 추천한.. 데이터들 가져올 때)

// function makeListPost(article, index) {
//     let tags ='';
//     for (let i=0; i<article['tags'].length; i++) {
//         console.log(article['tags'][i])
//         tags += " #"+article['tags'][i]['name'];
//     }
//     let tempHtml = `<tr>
//                 <th scope = "row">${index}</th>
//                 <td><a href="/view?idx=${article['idx']}">${article['title']}</td>
//                 <td>${article['comments'].length}</td>
//                 <td>${tags}</td>
//                 <td>${article['createdAt']}</td>
//             </tr>`
//
//     $("#list-post").append(tempHtml);
//
//     console.log(tags);
//
// }

//
// function save() {

// }


//파이썬 버전 코드
//
// function save() {
//     let form_data = new FormData($('#upload-file')[0]);
//     let comment = $("#comment").val();
//     console.log(comment)
//     form_data.append("comment_give", comment)
//
//     $.ajax({
//         type: 'POST',
//         url: '/fileupload',
//         data: form_data,
//         processData: false,
//         contentType: false,
//         success: function (response) {
//             console.log(response['doc2'])
//             let foodurl = response['doc2']['url']
//             let name = response['doc2']['name']
//             let comment = response['doc2']['comment']
//             let category = response['doc2']['category']
//             let emotion = response['doc2']['emotion']
//             console.log(foodurl, name, comment)
//             let temp_html = `<p class="question-style" style="margin-bottom: 10px;">END. 음식 추천 완료! </p>
//                             <progress class="progress is-normal" value="100" max="100">100%</progress>
//                             <div id="posting_result_img" style="background-image:url('${foodurl}')"></div>
//                             <div class="posting_result">
//                                 <p class="posting_title">당신의 추천 음식</p>
//                                 <p class="posting_foodname">${name}</p>
//                                 <p class="posting_comment">${comment}</p>
//                                 <p>
//                                 <span class="tag is-warning posting_tag" style="background-color: #FED7BF;">${name}</span>
//                                 <span class="tag is-warning posting_tag" style="background-color: #FED7BF;">존맛탱</span>
//                                 <span class="tag is-warning posting_tag" style="background-color: #FED7BF;">추천감사</span>
//                                 </p>
//                             </div>
//                             <div class="button-group-out">
//                                <button class="button next-stage" onclick="window.location.href='/'">홈</button>
//                             </div>
//                             `
//             let btnGroup = $('#button-group')
//             btnGroup.empty()
//             btnGroup.append(temp_html)
//         },
//     });
// }