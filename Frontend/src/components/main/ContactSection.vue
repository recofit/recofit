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
</style>