<template>
    <div class="body">
        <header-section />
        <search-section />
        <result-section />
        <popular-video-section />
        <like-video-section />
        <footer-section/>
    </div>
</template>

<script>
import HeaderSection from '@/components/common/SimpleHeaderSection.vue'
import SearchSection from '@/components/search/SearchSection.vue'
import ResultSection from '@/components/search/ResultSection.vue'
import FooterSection from '@/components/common/FooterSection.vue'
import PopularVideoSection from '@/components/search/PopularVideoSection.vue'
import LikeVideoSection from '@/components/search/LikeVideoSection.vue'


export default {
    components: {
        HeaderSection,
        SearchSection,
        ResultSection,
        PopularVideoSection,
        LikeVideoSection,
        FooterSection,
    }
}
</script>