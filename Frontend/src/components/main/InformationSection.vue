<template>
    <section class="information-section" id="information">
        <div class="container">
            <div class="row">
                <div class="information-img">
                    <div class="img-box">
                        <img :src="require(`@/assets/img/information.jpg`)">
                    </div>
                </div>
                <div class="information-content">
                    <div class="row">
                        <div class="section-title">
                            <h1> Website Information </h1>
                        </div>
                    </div>
                    <p>
                        <br>RECOFIT은 당신이 필요로 하는 서비스를 제공합니다.<br><br>
                        <br><b> 이렇게 사용하세요 </b><br><br>
                        - 당신이 살고 있는 장소와 하고자 하는 운동을 입력하세요.<br>
                        - RECOFIT이 추천하는 다양한 운동 장소를 확인하세요.<br>
                        - 다른 사용자의 리뷰를 통해 해당 장소에 대한 정보를 얻어보세요. <br>
                        - 추천 받은 장소가 당신의 마음에 들었다면, 예약 기능을 활용하세요.<br>
                        <br><br><b> 이런 분들에게 추천합니다 </b><br><br>
                        - 새롭게 운동을 시작하고 싶으신 분<br>
                        - 관심 있는 운동과 관련된 다양한 정보를 얻고 싶으신 분<br>
                        - 주변에 위치한 운동 장소를 간편하게 예약하고 싶으신 분<br>
                        - 운동 일정을 체계적으로 관리하고 싶으신 분<br>
                        <br><br>RECOFIT에서 제공하는 다양한 서비스를 마음껏 즐겨주시길 바랍니다.
                    </p>
                </div>
            </div>
        </div>
    </section>
</template>

<script>
export default {

}
</script>

<style scoped lang="css">
@import "@/css/mainPageStyle.css";

.information-section {
  padding: 80px 0 0;
}

.information-section .information-img {
  flex: 0 0 30%;
  max-width: 30%;
  margin-left: 6%;
  padding: 0 15px;
}

.information-section .information-img .img-box {
  position: relative;
}
.information-section .information-img .img-box img{
  width: 100%;
  display: block;
}

.information-section .information-content {
  flex: 0 0 58%;
  max-width: 58%;
  padding: 15px 15px;
  margin-left: 6%;
}

.information-section .section-title {
  font-size: 1.5rem;
  margin-bottom: 30px;
  padding: 0;
}
.information-section .section-title p {
  font-size: 1rem;
  color: #555555;
  line-height: 26px;
  margin: 0;
}

.information-content p {
  font-size: 1rem;
}
</style>