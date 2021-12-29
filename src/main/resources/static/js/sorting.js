"use strict";

function showFood() {
    let sorting = $("#sorting option:selected").val();
    console.log(sorting);

    $('#foodList').empty();
    // addFood();
}

function addFood() {
    let sortList = `${e}`;
    $.ajax({
        type: "GET",
        url: `${apiUrl}/food?query=${query}`,
        success: function (response) {
            return `
                    <div id="${foodList['id']}"  class="foodListCard">
                        <div class="foodImage" style='background-image:url("${foodList['imageUrl']}")' data-bs-toggle="modal" data-bs-target="#exampleModal${foodList['id']}"></div>
                        <div class="foodInfo">
                            <p class="foodListCardName">${foodList['name']}</p>
                            <i class="bi ${class_heart}" id="heart${foodList['id']}" onclick="heart('${foodList['id']}')"></i><span class="likedLength">${foodList['cnt']}</span>
                            <p class="foodListCardTags" id="tags${foodList['id']}"></p>
                        </div>
                    </div>
                    <div class="modal fade" id="exampleModal${foodList['id']}">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">${foodList['name']}에 대해 댓글을 남겨 주세요!</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <ul class="list-group" id="comment-list">
                                    <li class="list-group-item">댓글</li>
                                </ul>
                                <div class="modal-footer">
                                    <textarea class="form-control" id="comment" rows="3"></textarea>
                                    <button type="button" class="btn btn-primary" onclick="setArticleComment()">댓글작성</button>
                                </div>
                            </div>
                        </div>
                    </div>`
        }
    })
}