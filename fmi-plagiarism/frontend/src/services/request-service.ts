import axios from 'axios'

export const api = axios.create({baseURL: '/api'})

export const testFun = async () => api.get(`test`).then(r=> r.data)