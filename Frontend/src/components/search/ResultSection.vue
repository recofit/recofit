<template>
    <div class="section-title" id="result"><h1> 검색 결과 </h1></div>

    <table class="table">
        <thead>
            <tr>
            <th scope="col">번호</th>
            <th scope="col">이름</th>
            <th scope="col">위치</th>
            <th scope="col">평점</th>
            </tr>
        </thead>
        
        <tbody>
            <tr>
                <td>더미1</td>
                <td>더미2</td>
                <td>더미3</td>
                <td>더미4</td>
            </tr>
            <!-- <tr v-for="(review, index) in reviews" :key="index" data-bs-toggle="modal" data-bs-target="#detailModal" @click="detailReview(review)">
                <td>{{index + 1}}</td>
                <td>{{review.title}}</td>
                <td>{{review.name}}</td>
                <td>{{review.regDate}}</td>
            </tr> -->
        </tbody>
    </table>
</template>

<script>
export default {

}
</script>

<style scoped>
.section-title {
    margin-left: 10%;
    margin-top: 5%;
}

.view-title {
    margin-top: 0;
    margin-bottom: 2%;
}

.table {
    width: 80%; 
    margin: 0 auto;
    margin-bottom: 100px;
}

tbody tr:hover,
tbody tr:focus,
tbody tr:active {
    background-color: lightgray;
}
</style>