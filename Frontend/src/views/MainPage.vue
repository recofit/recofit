<template>
  <div class="body">
    <!-- Header Start -->
    <header class="header">
        <div class="container">
            <div class="row justify-content-between align-items-center">
                <span class="brand-name">
                    <a href="#" class="left">RECOFIT</a>
                    <nav class="nav">
                        <ul>
                            <li><a href="#carouselExample">Home</a></li>
                            <li><a href="#information">Information</a></li>
                            <li><a href="#team">Contact</a></li>
                            <li><a href="#">Search</a></li>
                            <li><a href="#"></a></li>
                            <li><a href="/sign">Login</a></li>
                        </ul>
                    </nav>
                </span>
            </div>
        </div>
    </header>
    <!-- Header End -->

    <!-- Carousel Section start -->
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
                  <p>당신의 경험이 누군가에게 도움이 될 것 입니다</p>
                </div>
            </div>
            <div class="carousel-item">
                <img :src="require(`@/assets/img/carousel3.jpg`)" class="d-block w-100" alt="..." />
                <div class="carousel-caption banner-row" style="color:white">
                  <h1>계획을 세워보세요</h1>
                  <p>더 오래, 더 빠르게 달릴 수 있을 것입니다</p>
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
    <!-- Carousel Section end -->

    <!-- Information Section Start -->
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
                        - RECOFIT은 이를 토대로 다양한 운동 장소를 추천합니다.<br>
                        - 다른 사용자의 리뷰를 통해 해당 장소에 대한 정보를 얻을 수 있습니다. <br>
                        - 추천 받은 장소가 당신의 마음에 들었다면, 예약 기능을 활용해보세요.<br>
                        <br><br><b> 이런 분들에게 추천합니다 </b><br><br>
                        - 새롭게 운동을 시작하고 싶으신 분<br>
                        - 관심 있는 운동과 관련된 다양한 정보를 얻고 싶으신 분<br>
                        - 주변에 위치한 운동 장소를 간편하게 예약하고 싶으신 분<br>
                        - 운동 일정을 체계적으로 관리하고 싶으신 분<br>
                        <br><br>RECOFIT에서 제공하고 있는 다양한 기능들을 마음껏 즐겨주시길 바랍니다.
                    </p>
                </div>
            </div>
        </div>
    </section>
    <!-- Information Section End -->

    <!-- Service Section Start -->
    <section class="page-section" id="services">
        <div class="container">
            <div class="text-center">
                <h2 class="section-heading text-uppercase">Services</h2>
            </div>
            <br>
            <div class="row text-center">
                <div class="col-md-4">
                    <span class="icon">
                        <i class="bi bi-search"></i>
                    </span>
                    <h4 class="my-3">Exercise Gym</h4>
                    <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Minima maxime quam architecto quo inventore harum ex magni, dicta impedit.</p>
                </div>
                <div class="col-md-4">
                    <span class="icon">
                        <i class="bi bi-calendar-date"></i>
                    </span>
                    <h4 class="my-3">Reservation Schedule</h4>
                    <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Minima maxime quam architecto quo inventore harum ex magni, dicta impedit.</p>
                </div>
                <div class="col-md-4">
                    <span class="icon">
                        <i class="bi bi-shield-check"></i>
                    </span>
                    <h4 class="my-3">Web Security</h4>
                    <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Minima maxime quam architecto quo inventore harum ex magni, dicta impedit.</p>
                </div>
            </div>
        </div>
    </section>
    <!-- Service Section End -->

    <hr>

    <!-- Introduction Section Start -->
    <section class="page-section" id="team">
        <div class="container">
            <div class="text-center section-title">
                <h2><strong>Development Team</strong></h2>
                <h3 class="section-subheading text-muted">
                    RECOFIT은 두 명의 개발자로부터 완성된 프로젝트입니다. <br>
                    우리는 RECOFIT을 통해 당신의 삶에 도움이 되기를 바랍니다. <br>
                    당신의 메시지로 인해 RECOFIT이 더 나은 서비스를 제공할 수 있습니다.
                </h3>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="team-member">
                        <img class="mx-auto rounded-circle" :src="require(`@/assets/img/khj.jpg`)" alt="..." />
                        <h4><i class="bi bi-github"></i> 김호진 </h4>
                        <p class="text-muted">Frontend Leader</p>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="team-member">
                        <img class="mx-auto rounded-circle" :src="require(`@/assets/img/lsh.jpg`)" alt="..." />
                        <h4><i class="bi bi-github"></i> 이승현</h4>
                        <p class="text-muted">Backend Leader</p>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Introduction Section End -->

    <!-- Contact Section Start -->
    <section class="contact-section" id="contact">
        <div class="container">
            <div class="row">
                <div class="contact-img">
                    <div class="img-box">
                        <div class="section-title">
                            <h1>Contact Us</h1>
                        </div>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="contact-form">
                    <!-- Start sending messages to the database over the backend -->
                    <form class="form" action="sellerMessage.jsp" method="post">
                        <div class="row">
                            <div class="left input-group">
                                <label>Name</label>
                                <input type="text" name="name" placeholder="이름을 입력해주세요" class="input-control">
                            </div>
                            <div class="right input-group">
                                <label>Email</label>
                                <input type="email" name="email" placeholder="이메일을 입력해주세요" class="input-control">
                            </div>
                        </div>
                        <div class="row">
                            <div class="input-group">
                                <label>Message</label>
                                <textarea class="input-control" name="message" placeholder="당신의 의견을 적어주세요"></textarea>
                            </div>
                        </div>
                        <div class="form-btn">
                            <button class="btn-style">Send Message</button>
                        </div>
                    </form>
                    <!-- End sending messages to the database over the backend -->
                </div>
            </div>

            <div class="row">
                <div class="contact-info">
                    <div class="row">
                        <div class="info-item">
                            <h5>Address</h5>
                            <p>Daejeon Rebulic of Korea, 16419</p>
                        </div>
                        <div class="info-item">
                            <h5>Phone</h5>
                            <p>82 042-000-0000</p>
                        </div>
                        <div class="info-item">
                            <h5>Email</h5>
                            <p>recofit@edu.ssafy.com</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Contact Section End -->

    <!-- Copyright text -->
    <p class="copyright">&copy; 2023 Kim Ho Jin & Lee Seung Hyun</p>
  </div>
</template>

<script>
export default {

}
</script>

<style scoped lang="css">
@import "../css/mainPageStyle.css";
</style>
