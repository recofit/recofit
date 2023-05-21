<template>
    <div id="carouselExample" class="carousel carousel-dark slide">
        <div class="carousel-inner">
            <div class="carousel-item active">
                <img :src="require(`@/assets/img/carousel1.jpg`)" class="d-block w-100" alt="..." />
                <div class="carousel-caption banner-row" style="color:white">
                  <h1>운동을 배워보세요</h1>
                  <p>멋진 장소들이 주변에 있습니다</p>
                </div>
            </div>
            <div class="carousel-item">
                <img :src="require(`@/assets/img/carousel2.jpg`)" class="d-block w-100" alt="..." />
                <div class="carousel-caption banner-row" style="color:white">
                  <h1>경험을 공유해주세요</h1>
                  <p>당신의 경험이 누군가에게 도움이 됩니다</p>
                </div>
            </div>
            <div class="carousel-item">
                <img :src="require(`@/assets/img/carousel3.jpg`)" class="d-block w-100" alt="..." />
                <div class="carousel-caption banner-row" style="color:white">
                  <h1>계획을 세워보세요</h1>
                  <p>더 오래, 더 빠르게 달릴 수 있습니다</p>
                </div>
            </div>
        </div>
        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExample" data-bs-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Previous</span>
        </button>
        <button class="carousel-control-next" type="button" data-bs-target="#carouselExample" data-bs-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="visually-hidden">Next</span>
        </button>
        <br>
    </div>
</template>

<script>
export default {

}
</script>

<style scoped lang="css">
@import "@/css/mainPageStyle.css";
</style>