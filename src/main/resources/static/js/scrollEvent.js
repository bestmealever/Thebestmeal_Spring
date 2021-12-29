"use strict";

// 무한스크롤 실패한 코드
// 한 번에 가져올 게시글의 개수
const defaultFoodPaginationSize = 15;

const getScrollTop = function() {
    return(window.pageXOffset !== undefined) ? window.pageXOffset : (document.documentElement || document.parentNode || document.body).scrollTop;
};

const getDocumentHeight = function() {
    const body = document.body;
    const html = document.documentElement;

    return Math.max(
        body.scrollHeight, body.offsetHeight,
        html.clientHeight, html.scrollHeight, html.offsetHeight
    );
};

const onScroll = function() {
    if (getScrollTop() === getDocumentHeight() - window.innerHeight) {
        const foodCards = document.querySelectorAll('#FoodList');

        const lastFoodId = Array.from(foodCards).map(function(card) {
            return parseInt(card.id, 10);
        }).reduce(function(previous, current) {
            return previous > current ? current : previous;
        });

        LikedService.fetchFoodPages(lastFoodId, defaultFoodPaginationSize);
    }
};

// $.ajax ({
//     type: 'GET',
//     url: `${apiUrl}/food`,
//     data: {size: size, lastFoodId: lastFoodId},
//     dataType: 'Json',
//     success: function(response) {
//         console.log(response);
//         if (localStorage.getItem("token")) {
//             let class_heart = response[i]['likedFood'].length === 1 ? "bi-heart-fill" : "bi-heart"
//             let foodList = response[i]
//             console.log(foodList);
//             console.log(foodList['id']);
//             console.log(foodList['likedFood'])
//             let temp = `
//                                 <div id="${foodList['id']}"  class="foodListCard">
//                                     <div class="foodImage" style='background-image:url("${foodList['imageUrl']}")' data-bs-toggle="modal" data-bs-target="#exampleModal${foodList['id']}"></div>
//                                     <div class="foodInfo">
//                                         <p class="foodListCardName">${foodList['name']}</p>
//                                         <i class="bi ${class_heart}" id="heart${foodList['id']}" onclick="heart('${foodList['id']}')"></i><span class="likedLength">${foodList['cnt']}</span>
//                                         <p class="foodListCardTags" id="tags${foodList['id']}"></p>
//                                     </div>
//                                 </div>
//                                 <div class="modal fade" id="exampleModal${foodList['id']}">
//                                     <div class="modal-dialog">
//                                         <div class="modal-content">
//                                             <div class="modal-header">
//                                                 <h5 class="modal-title" id="exampleModalLabel">${foodList['name']}에 대해 댓글을 남겨 주세요!</h5>
//                                                 <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
//                                             </div>
//                                             <ul class="list-group" id="comment-list">
//                                                 <li class="list-group-item">댓글</li>
//                                             </ul>
//                                             <div class="modal-footer">
//                                                 <textarea class="form-control" id="comment" rows="3"></textarea>
//                                                 <button type="button" class="btn btn-primary" onclick="setArticleComment()">댓글작성</button>
//                                             </div>
//                                         </div>
//                                     </div>
//                                 </div>
//                                     `
//             $('#foodList').append(temp);
//             Tag(foodList);
//         } else {
//             let foodList = response[i]
//             console.log(foodList);
//             console.log(foodList['id'])
//             let temp = `
//                                 <div id="${foodList['id']}"  class="foodListCard">
//                                     <div class="foodImage" style='background-image:url("${foodList['imageUrl']}")'></div>
//                                     <div class="foodInfo">
//                                         <p class="foodListCardName">${foodList['name']}</p>
//                                         <i class="bi bi-heart" id="heart${foodList['id']}" onclick="alert('로그인 후 이용해주세요!')"></i><span class="likedLength">${foodList['cnt']}</span>
//                                         <p class="foodListCardTags" id="tags${foodList['id']}"></p>
//                                     </div>
//                                 </div>
//                                     `
//             $('#foodList').append(temp);
//             Tag(foodList);
//         }
//     }
// })