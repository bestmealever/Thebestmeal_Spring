let postings;
let postingsAccepted;
let postingsDeclined;
let postingFoods;
let postingAcceptedFoods;
let postingDeclinedFoods;

$(document).ready(function () {
    //처음 출력 내가 추천받은 음식
    $.ajax({
        type: "GET",
        url: `${apiUrl}/adminposting`,
        success: function (response) {
            console.log(response)
            //foodDB 음식정보 받아오기
            user = response['user']
            postings = response['postings']
            postingsAccepted = response['postingsAccepted']
            //아직 declined 구현 안함.
            postingsDeclined = response['postingsDeclined']
            postingFoods = response['postingFoods']
            postingAcceptedFoods = response['postingAcceptedFoods']
            postingDeclinedFoods =response['postingDeclinedFoods']

            // postingsAccepted = response['postingsAccepted']
            // //아직 declined 구현 안함.
            // postingsDeclined = response['postingsDeclined']
            allPostingList()
        }
    })
})

//모든 포스팅 리스트업하기

function allPostingList() {
    console.log(postings, '전체 postinglist');
    let temp_nav = `<div class="admin-nav" style=" clear:left; margin-top: 30px;">
                        <a href="#" style="text-decoration: none;  border-bottom: 2px solid; padding-bottom: 10px; color:black; margin-right: 12px; margin-left: 10px;"><strong>추천하기 음식리스트</strong></a>
                        <a href="#" onclick="AcceptedList();" style="text-decoration: none;color:black; margin-right: 12px; margin-left: 10px;"><strong>수락 완료된 음식</strong></a>
                        <a href="#" onclick="DeclinedList();" style="text-decoration: none;color:black; margin-right: 12px; margin-left: 10px;"><strong>거절된 음식</strong></a>
                        <hr style="margin-top:8px;">
                    </div>
                    <div id="user-nav"></div>`
    $('#user-table').append(temp_nav);
    $('#adminPostingList').empty();
    for(let i=0; i<postings.length;i++) {
        //음식 DB detail 과  포스팅 DB 를 모두 받아오기
        //foodList: [i] - posting 된 것 중에 food 객체만 가져옴
        //posting:[i] - posting 전체 리스트
        let foodList = postingFoods[i]
        let posting = postings[i]

        console.log(posting['id'])

        //작성자는 heart 를 누르거나 갯수를 볼 필요도 없다. 애초에 아래 것은 빼주기.
        // <i class="bi bi-heart" id="heart" onclick=""></i><span class="likedLength">${foodList['likedFood'].length}</span>
        let temp = `<div id="${foodList['id']}"  class="foodListCard">
                        <div class="foodImage" style='background-image:url("${foodList['imageUrl']}")'></div>
                        <div class="foodInfo">
                            <p class="foodListCardName">${foodList['name']}</p>
                            <p class="foodListCardTags" id="tags${posting['id']}"></p>
                            <p>작성 일자 : ${posting['createdAt'].substr(0, 10)}</p>
                            <span class="badge text-dark" style="background-color: lightcoral; margin-left:5px;" id="posting${posting['id']}"  onclick="accept('${posting['id']}')">수락</span>
                            <span class="badge text-dark" style="background-color: lightblue; margin-left:5px;">거절</span>
                        </div>
                    </div>`
        $('#adminPostingList').append(temp);
        Tag_admin(foodList, posting['id']);
    }
}

//accept를 누르면, (1) posting의 status 가 accepted 로 바뀐다. (2)
function accept(id){
    console.log(postingsAccepted, 'Accepted postinglist');

    $.ajax({
        type: "POST",
        url: `${apiUrl}/postingaccepted/${id}`,
        success: function (response) {
            console.log(response);
            window.location.reload();
        }
    })
}

//decline을 누르면 (1) posting 의 status 가 declined 으로 바뀐다.
//tags{food_id} 로 해서, 단순화해줄 수는 없을까?
function Tag_admin(foodList, posting_id) {
    for (let i = 0; i < foodList['tags'].length; i++) {
        let tags = foodList['tags'][i]
        let tag = tags['tagName']
        if (tag === "korean") {
            let tag_korean = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">한식</span>`
            $(`#tags${posting_id}`).append(tag_korean);
        } else if (tag === "western") {
            let tag_western = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">양식</span>`
            $(`#tags${posting_id}`).append(tag_western);
        } else if (tag === "chinese") {
            let tag_chinese = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">중식</span>`
            $(`#tags${posting_id}`).append(tag_chinese);
        } else if (tag === "japanese") {
            let tag_japanese = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">일식</span>`
            $(`#tags${posting_id}`).append(tag_japanese);
        } else if (tag === "supper") {
            let tag_supper = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">야식</span>`
            $(`#tags${posting_id}`).append(tag_supper);
        } else if (tag === "snack") {
            let tag_snack = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">분식</span>`
            $(`#tags${posting_id}`).append(tag_snack);
        } else if (tag === "fastfood") {
            let tag_fastfood = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">패스트푸드</span>`
            $(`#tags${posting_id}`).append(tag_fastfood);
        } else if (tag === "salad") {
            let tag_salad = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">샐러드</span>`
            $(`#tags${posting_id}`).append(tag_salad);
        } else if (tag === "many_time") {
            let tag_manytime = `<span class="badge text-dark" style="background-color: lightgray;margin-left:5px;">시간 많을 때</span>`
            $(`#tags${posting_id}`).append(tag_manytime);
        } else if (tag === "perfect") {
            let tag_perfect = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">완벽한 식사</span>`
            $(`#tags${posting_id}`).append(tag_perfect);
        } else if (tag === "stressed") {
            let tag_stressed = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">스트레스 받을 때</span>`
            $(`#tags${posting_id}`).append(tag_stressed);
        } else if (tag === "fatty") {
            let tag_fatty = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">기름 진거 땡길 때</span>`
            $(`#tags${posting_id}`).append(tag_fatty);
        } else if (tag === "no_time") {
            let tag_notime = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">시간 없을 떄</span>`
            $(`#tags${posting_id}`).append(tag_notime);
        } else if (tag === "needsugar") {
            let tag_needsugar = `<span class="badge text-dark" style="background-color: lightgray; margin-left:5px;">당 땡겨</span>`
            $(`#tags${posting_id}`).append(tag_needsugar);
        }
    }
}