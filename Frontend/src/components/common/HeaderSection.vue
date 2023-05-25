<template>
  <header class="header">
    <div class="container">
      <div class="row justify-content-between align-items-center">
        <span class="brand-name">
          <a href="/" class="left">RECOFIT</a>
          <nav class="nav">
            <ul>
              <li><a href="#carouselExample">Home</a></li>
              <li><a href="#information">Information</a></li>
              <li><a href="#team">Contact</a></li>
              <li><a href="/search">Search</a></li>
              <li><a href="#"></a></li>
              <li><a v-if="id == null" href="/sign">Login</a></li>
              <li><a v-if="id" :href="`/mypage/${id}`">myPage</a></li>
              <li><a v-if="id" href="/" @click="logout">Logout</a></li>
            </ul>
          </nav>
        </span>
      </div>
    </div>
  </header>
</template>

<script>
export default {
  data() {
    return {
      id: null,
    };
  },
  created() {
    let key = sessionStorage.getItem("loginUser");
    if (key != null) {
      this.id = JSON.parse(sessionStorage.getItem("loginUser")).id;
    } else {
      this.id = null;
    }
  },
  methods: {
    logout() {
      this.$store.dispatch("logout");
    },
  },
};
</script>

<style scoped>
.header {
  position: fixed;
  left: 0;
  top: 0;
  width: 100%;
  z-index: 99;
  background-color: #ffffff;
  transition: all 0.5s ease;
}

.header .brand-name {
  padding: 0 15px;
}
.header .brand-name a {
  text-decoration: none;
  font-size: 40px;
  color: #285c4d;
  text-transform: uppercase;
  font-weight: 800;
  letter-spacing: 2px;
  transition: all 0.5s ease;
}
.header .brand-name a:hover {
  color: #17382f;
}

.header .nav {
  float: right;
}
.header .nav ul {
  list-style: none;
  margin: 0;
  padding: 0;
}
.header .nav ul li {
  display: inline-block;
  margin-left: 30px;
}
.header .nav ul li a {
  text-decoration: none;
  font-size: 16px;
  font-weight: 700;
  color: #285c4d;
  margin: 20px 0;
  display: block;
  transition: all 0.5s ease;
}
.header .nav ul li a:hover {
  color: #17382f;
}
</style>
