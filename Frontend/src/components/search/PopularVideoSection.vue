<template>
    <div class="section-title"><h1> 인기 영상 </h1></div>

    <h3 class="view-title"><strong>조회수 <span class="color">TOP</span> 영상</strong></h3>
    <div class="video-section">
        <div class="box">
            <div id="popular-video" class="carousel carousel-dark slide">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <div class="container container-top">
                            <div class="row" >
                                <div class="col">
                                    <div class="card" v-for="(video, idx) in videoInfo1.slice(0, 2)" :key="video.id" :video="video">
                                        <img :src="`http://i.ytimg.com/vi/${video.id}/maxresdefault.jpg`" @click="clickVideo(video)" data-bs-toggle="modal" data-bs-target="#youtubeModal"/>
                                        <div class="card-body">
                                            <span class="card-text channel">{{videoChannel1[idx]}}</span><br>
                                            <div class="row">
                                                <div class="col">
                                                    <div class="card-text"><i class="bi bi-eye"></i> {{video.statistics.viewCount}} </div><br>
                                                    <div class="card-text"> <i class="bi bi-hand-thumbs-up"></i> {{video.statistics.likeCount}} </div><br>
                                                </div>
                                                <div class="col">
                                                    <div class="heart">
                                                        <i v-if="!videoState1[idx]" @click="subscribe(idx, video, videoChannel1[idx])" class="bi bi-heart"></i>
                                                        <i v-if="videoState1[idx]" @click="unsubscribe(idx, video)" class="bi bi-heart-fill"></i>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <!-- <div class="card">
                                        <img :src="require(`@/assets/img/carousel1.jpg`)" data-bs-toggle="modal" data-bs-target="#youtubeModal" />
                                        <div class="card-body">
                                            <span class="card-text channel">채널명</span><br>
                                            <div class="row">
                                                <div class="col">
                                                    <div class="card-text"><i class="bi bi-eye"></i> 3451002 </div><br>
                                                    <div class="card-text"> <i class="bi bi-hand-thumbs-up"></i> 10452  </div><br>
                                                </div>
                                                <div class="col">
                                                    <div class="heart" @click="doSubscribe(idx)">
                                                        <i v-if="!videoState1[idx]" class="bi bi-heart"></i>
                                                        <i v-if="videoState1[idx]" class="bi bi-heart-fill"></i>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <img :src="require(`@/assets/img/carousel2.jpg`)" data-bs-toggle="modal" data-bs-target="#youtubeModal" />
                                        <div class="card-body">
                                            <span class="card-text channel">채널명</span><br>
                                            <div class="row">
                                                <div class="col">
                                                    <div class="card-text"><i class="bi bi-eye"></i> 3451002 </div><br>
                                                    <div class="card-text"> <i class="bi bi-hand-thumbs-up"></i> 10452  </div><br>
                                                </div>
                                                <div class="col">
                                                    <div class="heart" @click="doSubscribe(idx)">
                                                        <i v-if="!state" class="bi bi-heart"></i>
                                                        <i v-if="state" class="bi bi-heart-fill"></i>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="card">
                                        <img :src="require(`@/assets/img/carousel3.jpg`)" data-bs-toggle="modal" data-bs-target="#youtubeModal" />
                                        <div class="card-body">
                                            <span class="card-text channel">채널명</span><br>
                                            <div class="row">
                                                <div class="col">
                                                    <div class="card-text"><i class="bi bi-eye"></i> 3451002 </div><br>
                                                    <div class="card-text"> <i class="bi bi-hand-thumbs-up"></i> 10452  </div><br>
                                                </div>
                                                <div class="col">
                                                    <div class="heart">
                                                        <i class="bi bi-heart"></i>
                                                        <i class="bi bi-heart-fill"></i>
                                                    </div>
                                                </div>
                                            </div>
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
                                    <div class="card" v-for="video in videos1.slice(3, 6)" :key="video.id.videoId" :video="video">
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
                
                <button class="carousel-control-prev" type="button" data-bs-target="#popular-video" data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Previous</span>
                </button>
    
                <button class="carousel-control-next" type="button" data-bs-target="#popular-video" data-bs-slide="next">
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
    name: "PopularVideoSection",
    data() {
        return {
            state: true,
        };
    },
    components: { 
        YoutubeModal,
    },
    methods: {
        clickVideo(video) {
            this.$store.dispatch('clickVideo', video);
        },
        subscribe(idx, video, channelName) {
            this.$store.state.videoState1[idx] = true;
            this.$store.dispatch("doSubscribe", {video, channelName});
        },
        unsubscribe(idx, video) {
            this.$store.state.videoState1[idx] = false;
            this.$store.dispatch("doUnsubscribe", video);
        },   
    },
    computed: {
        ...mapState(['videoChannel1', 'videoInfo1', 'videoState1']),
    },
    created() {
        // this.$store.dispatch("searchPopularYoutube", "다이어트");
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
    height: 110px;
}
.card-text {
    height: 5%;
    margin-top: -3%;
    font-weight: bold;
    text-align: left;
    line-height: 2rem;
}

.channel {
    color: #285c4d;
    margin-bottom: 1%;
}

img {
    width: 100%;
    height: 220px;
}

.stat {
    margin-left: 20%;
}

.heart {
    margin-right: 20%;
    text-align: right;
}

.bi-heart, .bi-heart-fill {
    font-size: 30px;
    color: rgb(204, 99, 99);
}

.color {
    color: rgb(204, 99, 99)
}
</style>