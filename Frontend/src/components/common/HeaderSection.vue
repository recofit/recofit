<template>
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
                            <li><a href="/mypage">myPage</a></li>
                            <li><a href="/" @click="logout">Logout</a></li>
                        </ul>
                    </nav>
                </span>
            </div>
        </div>
    </header>
</template>

<script>
export default {
    methods: {
        logout() {
            this.$store.dispatch('logout');
        },
    }
}
</script>

<style scoped lang="css">
@import "@/css/mainPageStyle.css";
</style>
