import axios from 'axios'

export const api = axios.create({baseURL: '/api'})

export const getAllDataFiles = async (): Promise<DataFiles[]> =>
    api.get<DataFiles[]>(`/all`).then(r => r.data)

export const getAllVerifiedDataFiles = async (): Promise<DataFiles[]> =>
    api.get<DataFiles[]>(`/verified`).then(r => r.data)

export const getAllNonVerifiedDataFiles = async (): Promise<DataFiles[]> =>
    api.get<DataFiles[]>(`/non-verified`).then(r => r.data)

export const getAllPlagiarismDetectedDataFiles = async (): Promise<DataFiles[]> =>
    api.get<DataFiles[]>(`/plagiarism-detected`).then(r => r.data)

export const getAllPlagiarismNotDetectedDataFiles = async (): Promise<DataFiles[]> =>
    api.get<DataFiles[]>(`/plagiarism-not-detected`).then(r => r.data)