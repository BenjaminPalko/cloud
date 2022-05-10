<template>
  <h1>{{ gateway_uri }}</h1>
  <li v-for="video in videos" :key="video.id">
    <VideoModal :video-info="video" />
  </li>
</template>

<script setup lang="ts">
import type { Ref } from "vue";
import type { Video } from "@/types/video";
import { onMounted, ref } from "vue";
import VideoModal from "@/components/VideoModal.vue";
import axios from "axios";

const showModal = ref(false);
const gateway_uri = ref(import.meta.env.VITE_GATEWAY_URI);
const videos: Ref<Video[]> = ref([]);
onMounted(async () => {
  videos.value = await retrieveVideos();
});

async function retrieveVideos(): Promise<Video[]> {
  try {
    const response = await axios.get(`${gateway_uri.value}/video`);
    return response.data;
  } catch (e) {
    console.error(e);
    return [];
  }
}
</script>

<style scoped></style>
