import {createApp} from 'vue'
import {Dialog, Notify, Quasar} from 'quasar'

import App from './App.vue'

createApp(App)
    .use(Quasar, {
        plugins: {
            Dialog, Notify
        },
        config: {
            loadingBar: {
                color: 'purple',
                size: '200px',
                position: 'bottom'
            }
        }
    }).mount("#app")