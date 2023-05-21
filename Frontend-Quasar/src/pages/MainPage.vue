<template>
  <q-page class="flex flex-center">
    <body>
    <!-- Header Start -->
    <header class="header">
        <div class="container">
            <div class="row justify-content-between align-items-center">
                <div class="brand-name">
                    <a href="#">RECOFIT</a>
                </div>
                <nav class="nav">
                    <ul>
                        <li><a href="#banner">Home</a></li>
                        <li><a href="#information">Information</a></li>
                        <li><a href="#contact">Contact</a></li>
                        <li><a href="./sellerSalesListPage.jsp">Search</a></li>
                        <li><a href="#"></a></li>
                        <li><a href="./homePage.html">Logout</a></li>
                    </ul>
                </nav>
            </div>
        </div>
    </header>
    <!-- Header End -->

    <!-- Banner Section Start -->
    <section class="banner-section" id="banner">
            <div class="slider">
                    <div class="swiper-container">
                            <div class="swiper-wrapper">
                                    <div class="swiper-slide ss1">
                                            <div class="banner-container">
                                                    <div class="banner-row">
                                                            <h2>어디서 운동할까?</h2>
                                                            <p>Fitstargram을 위한 다양한 장소를 추천해드립니다</p>
                                                    </div>
                                            </div>
                                    </div>
                                    <div class="swiper-slide ss2">
                                            <div class="banner-container">
                                                    <div class="banner-row">
                                                            <h2>해리포터 <em>Harry Potter</em></h2>
                                                            <p>The stories we love best do live in us forever.<br>
                                                                So whether you come back by page or by the big screen,<br>
                                                                Hogwarts will always be there to welcome you home.</p>
                                                    </div>
                                            </div>
                                    </div>
                                    <div class="swiper-slide ss3">
                                            <div class="banner-container">
                                                    <div class="banner-row">
                                                            <h2>셜록 홈즈 <em>Sherlock Holmes</em></h2>
                                                            <p>When you have eliminated the impossible,<br>
                                                                whatever remains, however improbable, must be the truth.</p>
                                                    </div>
                                            </div>
                                    </div>
                            </div>
                            <div class="swiper-pagination"></div>
                            <div class="swiper-button-prev"></div>
                            <div class="swiper-button-next"></div>
                    </div>
            </div>
    </section>
    <!-- Banner Section End -->

    <!-- Service Section Start -->
    <!-- <section class="page-section" id="services">
        <div class="container">
            <div class="text-center">
                <h2 class="section-heading text-uppercase">Services</h2>
                <h3 class="section-subheading text-muted">Lorem ipsum dolor sit amet consectetur.</h3>
            </div>
            <div class="row text-center">
                <div class="col-md-4">
                    <span class="fa-stack fa-4x">
                        <i class="fas fa-circle fa-stack-2x text-primary"></i>
                        <i class="fas fa-shopping-cart fa-stack-1x fa-inverse"></i>
                    </span>
                    <h4 class="my-3">E-Commerce</h4>
                    <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Minima maxime quam architecto quo inventore harum ex magni, dicta impedit.</p>
                </div>
                <div class="col-md-4">
                    <span class="fa-stack fa-4x">
                        <i class="fas fa-circle fa-stack-2x text-primary"></i>
                        <i class="fas fa-laptop fa-stack-1x fa-inverse"></i>
                    </span>
                    <h4 class="my-3">Responsive Design</h4>
                    <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Minima maxime quam architecto quo inventore harum ex magni, dicta impedit.</p>
                </div>
                <div class="col-md-4">
                    <span class="fa-stack fa-4x">
                        <i class="fas fa-circle fa-stack-2x text-primary"></i>
                        <i class="fas fa-lock fa-stack-1x fa-inverse"></i>
                    </span>
                    <h4 class="my-3">Web Security</h4>
                    <p class="text-muted">Lorem ipsum dolor sit amet, consectetur adipisicing elit. Minima maxime quam architecto quo inventore harum ex magni, dicta impedit.</p>
                </div>
            </div>
        </div>
    </section> -->
    <!-- Service Section End -->

    <!-- Information Section Start -->
    <section class="information-section" id="information">
        <div class="container">
            <div class="row">
                <div class="information-img">
                    <div class="img-box">
                        <img src="../assets/img/skku_tree.jpg">
                    </div>
                </div>
                <div class="information-content">
                    <div class="row">
                        <div class="section-title">
                            <h1> Website Information </h1>
                        </div>
                    </div>
                    <p>
                        RECOFIT은 당신만을 위한 운동 장소를 추천합니다.<br>
                        <br><b> For Sellers, </b><br>
                        - Take out the unread books from your bookshelf and put them on the market.<br>
                        - Pass on the major books that you don't use anymore to your juniors.<br>
                        - Books priced at reasonable prices will be popular.<br>
                        - Buyers will look around the site and look at the books they are interested in.<br>
                        <br><b> For Buyers, </b><br>
                        - Buy the book you wanted to read at reasonable price.<br>
                        - Find the major books need for your class.<br>
                        - The popular book will be sold soon, so hurry up.<br>
                        - Sellers will offer various books for you at reasonable price.<br>
                        <br>Please feel free to enjoy the service provided by the website.
                    </p>
                </div>
            </div>
        </div>
        <br><br><br>
    </section>
    <!-- Information Section End -->

    <hr>

    <!-- Introduction Section Start -->
    <section class="page-section bg-light" id="team">
        <div class="container">
            <div class="text-center section-title">
                <h2><strong>Development Team</strong></h2>
                <h3 class="section-subheading text-muted">
                    RECOFIT은 두 명의 개발자로부터 완성된 프로젝트입니다
                </h3>
            </div>
            <div class="row">
                <div class="col-lg-6">
                    <div class="team-member">
                        <img class="mx-auto rounded-circle" src="../assets/img/jin.jpg" alt="..." />
                        <h4>김호진</h4>
                        <p class="text-muted">Frontend Leader</p>
                    </div>
                </div>
                <div class="col-lg-6">
                    <div class="team-member">
                        <img class="mx-auto rounded-circle" src="../assets/img/hyun.jpg" alt="..." />
                        <h4>이승현</h4>
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
                            <button class="btn btn-style">Send Message</button>
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
                            <p>ssafy@ssafy.com</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </section>
    <!-- Contact Section End -->

    <!-- Copyright text -->
    <p class="copyright">&copy; 2023 Kim Ho Jin & Lee Seung Hyun</p>
    </body>

    <!-- <script src="js/main.js"></script>
    <script src="js/jquery-3.5.1.min.js"></script>
    <script src="js/swiper.min.js"></script>
    <script>
            let mySwiper = new Swiper(".swiper-container", {
                    navigation: {
                            nextEl: ".swiper-button-next",
                            prevEl: ".swiper-button-prev",
                    },
                    pagination: {
                            el: ".swiper-pagination",
                            type: "bullets",
                    },
                    autoplay: {
                            delay: 5000,
                    },
            })
    </script> -->
  </q-page>
</template>

<script>
import { ref } from 'vue'

export default {
  setup () {
    return {
      slide: ref(1)
    }
  }
}
</script>

<style scoped>
@import "../css/mainPageStyle1.css";
@import "../css/mainPageStyle2.css";
@import "../css/swiper.css";
</style>
