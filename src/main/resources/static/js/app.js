console.log("App running!");

const endpoint = "http://localhost:8080/api/";

window.addEventListener("load", startApp);

function startApp() {
    document.getElementById('send').addEventListener('click', getMovie);
}

async function getMovie(e) {
    e.preventDefault();

    // Show loading symbol
    showLoading();

    console.log(`${endpoint}movie?topic=${document.getElementById('topic').value}`);
    const response = await fetch(`${endpoint}movie?topic=${encodeURIComponent(document.getElementById('topic').value)}`);
    console.log(response);

    // Hide loading symbol
    hideLoading();

    const data = await response.json();
    console.log(data);
    populateMovieTableData(data);
}

function populateMovieTableData(data) {
    document.querySelector("#movie-table-body").innerHTML = "";
    document.querySelector("#movie-poster").src = "";

    const movieTableBody = /*html*/ `
        <tr>
            <td>${data.Title}</td>
            <td>${data.Runtime}</td>
            <td>${data.Year}</td>
            <td>${data.Rated}</td>
        </tr>
    `;

    document.querySelector("#movie-table-body").insertAdjacentHTML("beforeend", movieTableBody);
    document.querySelector("#movie-poster").src = `${data.Poster}`;
}

function showLoading() {
    const loadingElement = document.querySelector("#loading");
    loadingElement.style.display = "block"; // Show the loading symbol
}

function hideLoading() {
    const loadingElement = document.querySelector("#loading");
    loadingElement.style.display = "none"; // Hide the loading symbol
}
