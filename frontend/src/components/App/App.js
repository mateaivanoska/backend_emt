import './App.css';
import {Component} from "react";
import Book from "../Book/BookList/book"
import Categories from "../Categories/categories";
import libraryRepository from "../../repository/libraryRepository";
import {BrowserRouter as Router, Routes, Route} from 'react-router-dom';
import Author from "../Author/author";
import Header from "../Header/header";
import BookAdd from "../Book/BookAdd/BookAdd";
import BookEdit from "../Book/BookEdit/BookEdit";


//ne mi ja instalira verizjata na react-route-dom 6 zatoa i ne funkcionira so useNavigate


class App extends Component {

    constructor(props) {
        super(props);
        this.state = {
            categories: [],
            author: [],
            book: [],
            selectedBook: {}
        }
    }

    render() {
        return (

            <Router>

                <Header/>
                <main>
                    <Routes>

                        <Route path={"/book/edit/:id"}
                               element={<BookEdit author={this.state.author} categories={this.state.categories}
                                                  onEditBook={this.editBook} book={this.state.selectedBook}/>}/>
                        <Route path={"/book/add"}
                               element={<BookAdd author={this.state.author} categories={this.state.categories}
                                                 onAddBook={this.addBook}/>}/>
                        <Route path={"/book"}
                               element={<Book books={this.state.book} onDelete={this.deleteBook} onMark={this.markBook}
                                              onEdit={this.getBook}/>}/>
                        <Route path={"/categories"} element={<Categories categories={this.state.categories}/>}/>
                        <Route path={"/author"} element={<Author author={this.state.author}/>}/>
                        <Route path={"/"} element={<Book book={this.state.book}/>}/>

                    </Routes>
                </main>
            </Router>


        )

    }

    loadBook = () => {
        libraryRepository.fetchBooks()
            .then((data) => {
                this.setState({
                    book: data.data
                })
            });
    }
    loadCategories = () => {
        libraryRepository.fetchCategory()
            .then((data) => {
                this.setState({
                    categories: data.data
                })
            });
    }
    loadAuthors = () => {
        libraryRepository.fetchAuthors()
            .then((data) => {
                this.setState({
                    author: data.data
                })
            });
    }

    deleteBook = (id) => {
        libraryRepository.deleteBook(id)
            .then(() => {
                this.loadBook();
            });
    }

    markBook = (id) => {
        libraryRepository.markBook(id)
            .then(() => {
                this.loadBook();
            });
    }
    addBook = (name, category, author, availableCopies) => {
        libraryRepository.addBook(name, category, author, availableCopies)
            .then(() => {
                this.loadBook();
            });
    }
    getBook = (id) => {
        libraryRepository.getBook(id)
            .then((data) => {
                this.setState({
                    selectedBook: data.data
                })
            });
    }
    editBook = (id, name, category, author, availableCopies) => {
        libraryRepository.editBook(id, name, category, author, availableCopies)
            .then(() => {
                this.loadBook();
            });
    }

    componentDidMount() {
        this.loadCategories();
        this.loadAuthors();
        this.loadBook();
    }
}

export default App;
