import {createApp} from 'vue'
import 'material-icons/iconfont/material-icons.css';
import "quasar/dist/quasar.prod.css";
import App from './App.vue'
import {Dialog, Notify, Quasar} from "quasar";
import {createRouter, createWebHistory} from "vue-router";
import Home from "./pages/Home.vue"
import Administration from "./pages/Administration.vue"
import UploadFiles from "./pages/UploadFiles.vue"

export const routes = [
    {path: '/home', component: Home},
    {path: '/administration', component: Administration},
    {path: '/upload-files', component: UploadFiles},
    {
        path: '/',
        redirect: to => ({path: `/home`}),
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes,
})

createApp(App)
    .use(Quasar, {
        plugins: {
            Dialog,
            Notify
        },
        config: {
            loadingBar: {
                color: 'purple',
                size: '200px',
                position: 'bottom'
            }
        }
    })
    .use(router)
    .mount("#app");
