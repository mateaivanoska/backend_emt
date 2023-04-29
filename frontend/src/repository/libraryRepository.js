import axios from "../custom-axios/axios";


const LibraryService={
    fetchBooks: () => {
        return axios.get("/book");
    },
    fetchCategory:()=>{
        return axios.get("/categories")
    },
    fetchAuthors:()=>{
        return axios.get("/author")
    },
    deleteBook: (id) =>{
        return axios.delete(`/book/delete/${id}`);
    },
    addBook: (name,category,author,availableCopies) =>{
        return axios.post("/book/add",{
            "name":name,
            "category":category,
            "author":author,
            "availableCopies":availableCopies
        })
    },
    editBook: (id,name,category,author,availableCopies) =>{
        return axios.put(`/book/edit/${id}`,{
            "name":name,
            "category":category,
            "author":author,
            "availableCopies":availableCopies
        })
    },
    markBook: (id) =>{
        return axios.put(`/book/markTaken/${id}`);
    },
    getBook:(id)=>{
        return axios.get(`/book/${id}`);
    }
}

export default LibraryService;