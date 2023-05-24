<template>
    <h3 class="view-title"><strong>좋아요 <span class="color">TOP</span> 영상</strong></h3>
    <div class="video-section">
        <div class="box">
            <div id="like-video" class="carousel carousel-dark slide">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <div class="container container-top">
                            <div class="row" >
                                <div class="col">
                                    <div class="carousel-item active" style="width: 100%; overflow: hidden">
                                        <div class="container container-bottom">
                                            <div class="row">
                                                <div class="col">
                                                    <div class="card" v-for="video in videos2.slice(0, 2)" :key="video.id.videoId" :video="video">
                                                        <img :src="video.snippet.thumbnails.high.url" />
                                                        <div class="card-body">
                                                            <span class="card-text">{{video.snippet.channelTitle}}</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- <div class="card" data-bs-toggle="modal" data-bs-target="#youtubeModal">
                                        <img :src="require(`@/assets/img/carousel1.jpg`)" />
                                        <div class="card-body">
                                            <span class="card-text">채널명</span><br>
                                            <i class="bi bi-heart-fill"></i>
                                        </div>
                                    </div>
                                    <div class="card" data-bs-toggle="modal" data-bs-target="#youtubeModal">
                                        <img :src="require(`@/assets/img/carousel1.jpg`)" />
                                        <div class="card-body">
                                            <span class="card-text">채널명</span><br>
                                            <i class="bi bi-heart-fill"></i>
                                        </div>
                                    </div>
                                    <div class="card" data-bs-toggle="modal" data-bs-target="#youtubeModal">
                                        <img :src="require(`@/assets/img/carousel1.jpg`)" />
                                        <div class="card-body">
                                            <span class="card-text">채널명</span><br>
                                            <i class="bi bi-heart-fill"></i>
                                        </div>
                                    </div> -->
                                </div>
                            </div>
                        </div>
                    </div>

                    <!-- <div class="carousel-item" style="width: 100%; overflow: hidden">
                        <div class="container container-bottom">
                            <div class="row">
                                <div class="col">
                                    <div class="card" v-for="video in videos2.slice(3, 6)" :key="video.id.videoId" :video="video">
                                        <img :src="video.snippet.thumbnails.high.url" />
                                        <div class="card-body">
                                            <span class="card-text">{{video.snippet.channelTitle}}</span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div> -->

                </div>
                
                <button class="carousel-control-prev" type="button" data-bs-target="#like-video" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
    
                <button class="carousel-control-next" type="button" data-bs-target="#like-video" data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Next</span>
                </button>
            </div>
        </div>
    </div>
    <youtube-modal/>

</template>

<script>
import {mapState} from 'vuex'
import YoutubeModal from './modal/YoutubeModal.vue'

export default {
    name: "LikeVideoSection",
    components: { 
        YoutubeModal
    },
    methods: {
        clickVideo(video) {
            this.$store.dispatch('clickVideo', video);
        },
    },
    computed: {
        ...mapState(['videos2']),
    },
    created() {
        // this.$store.dispatch("searchLikeYoutube", "운동");
    }
}
</script>

<style scoped>
.video-section {
    width: 80%; 
    margin: 0 auto;
    margin-bottom : 5%;
}

.section-title {
    margin-left: 10%;
    margin-top: 5%;
}

.view-title {
    margin-left: 11%;
    margin-bottom: 2%;
}

.title {
    color:darkslategray;
}

.carousel-item:active {
    width: 100%; 
    overflow: hidden;
}

.box {
    position: relative;
    z-index: 1;
    padding: 10px;
    border-radius: 5px;
    box-shadow: 0 0 10px 0 rgba(0, 0, 0, 0.1), 0 5px 5px 0 rgba(0, 0, 0, 0.2);
    text-align: center;
    margin-bottom: 10ox;
}
.card {
    width: 25%;
    box-shadow: 8px 8px 8px rgba(0, 0, 0, 0.1);
    display: inline-block;
    margin: 2%;
    margin-top: 3%;
}
.card:hover {
    box-shadow: 8px 8px 8px rgba(0, 0, 0, 0.3);
}
.card-body {
    height: 100px;
}
.card-text {
    margin-top : 5px;
    color: #285c4d; 
    font-weight: bold;
}
img {
    width: 100%;
    height: 220px;
}
.bi-heart, .bi-heart-fill {
    font-size: 25px;
    color: rgb(204, 99, 99);
}

.color {
    color: rgb(204, 99, 99)
}
</style>