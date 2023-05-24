<template>
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
                    <div class="form">
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
                                <textarea class="input-control" name="message" placeholder="메시지를 적어주세요"></textarea>
                            </div>
                        </div>
                        <div class="form-btn">
                            <button class="btn-style">Send Message</button>
                        </div>
                    </div>
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
</template>

<script>
export default {

}
</script>

<style scoped lang="css">
@import "@/css/mainPageStyle.css";

.contact-section {
  width: 100%;
  padding: 0 0 80px;
}

.contact-section .contact-img {
  flex: 0 0 100%;
  max-width: 100%;
  padding: 0 15px;
}

.contact-section .contact-img .img-box {
  padding: 200px 0;
  background-image: url("@/assets/img/forest.jpg");
  background-size: cover;
  background-position: center;
}

.contact-section .section-title {
  text-align: center;
}
.contact-section .section-title h1 {
  color: #ffffff;
}
.contact-section .section-title h1:before {
  background-color: #ffffff;
  left: 50%;
  margin-left: -40px;
}

.contact-section .contact-form {
  flex: 0 0 100%;
  max-width: 100%;
  padding: 0px 65px;
}

.contact-section .contact-form .form {
  background-color: #f3f2f1;
  padding: 55px 40px;
  margin-top: -50px;
}
.contact-section .contact-form .form label {
  color: #000000;
  font-weight: 500;
  display: block;
  margin: 0 0 5px;
}

.contact-section .contact-form .form .input-group {
  flex: 0 0 100%;
  max-width: 100%;
  padding: 0 15px;
  margin-bottom: 30px;
}
.contact-section .contact-form .form .input-group.left,
.contact-section .contact-form .form .input-group.right {
  flex:0 0 50%;
  max-width: 50%;
}

.contact-section .contact-form .form .input-control {
  width: 100%;
  height: 45px;
  padding: 6px 0px ;
  color: #000000;
  font-size: 16px;
  background-color: transparent;
  border: none;
  border-bottom: 1px solid #cccccc;
}
.contact-section .contact-form .form .input-control:focus {
  outline: none;
}

.contact-section .contact-form .form textarea.input-control {
  height: 140px;
  resize: none;
  padding-top: 12px;
}

.contact-section .contact-form .form .form-btn {
  text-align: center;
}

.contact-section .contact-info {
  flex: 0 0 100%;
  max-width: 100%;
}

.contact-section .contact-info .info-item {
  flex: 0 0 33.33%;
  max-width: 33.33%;
  text-align: center;
  padding: 0 15px;
  margin-bottom: 40px;
}
.contact-section .contact-info .info-item h5 {
  font-size: 16px;
  font-weight: 600;
  margin: 0;
}
.contact-section .contact-info .info-item p {
  font-size: 16px;
  margin: 0;
  color: #555555;
}

.btn-style {
  background-color: #285c4d;
  color: #ffffff;
  border: solid 2px transparent;
  padding: 10px 35px;
  border-radius: 30px;
  line-height: 1.5;
  cursor: pointer;
  text-decoration: none;
  font-size: 16px;
  font-weight: 600;
  display: inline-block;
}
.btn-style:hover {
  background-color: transparent;
  border-color: #285c4d;
  color: #285c4d;
}
.btn-style:focus {
  outline: none;
}

.form-btn {
  margin-top: 5%;
}
</style>