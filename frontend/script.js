
document.addEventListener("DOMContentLoaded", () => {
    const loginBtn = document.getElementById("login-btn");
    const viewCoursesBtn = document.getElementById("view-courses-btn");
    const hideCoursesBtn = document.getElementById("hide-courses-btn");
    const createCourseBtn = document.getElementById("create-course-btn");
    const courseList = document.getElementById("course-list");
    const createCourseForm = document.getElementById("create-course-form");
    const courseForm = document.getElementById("course-form");
    const coursesDiv = document.getElementById("courses");

    loginBtn.addEventListener("click", () => {
        window.location.href = "auth.html";
    });


    // Загрузка и отображение курсов
    viewCoursesBtn.addEventListener("click", async () => {
        try {
            const response = await fetch("http://localhost:8080/platform/course/all");
            if (!response.ok) throw new Error("Failed to fetch courses");

            const courses = await response.json();
            coursesDiv.innerHTML = ""; // Очистка предыдущего списка

            courses.forEach(course => {
                const courseDiv = document.createElement("div");
                courseDiv.className = "course";
                courseDiv.innerHTML = `
                    <h3>${course.name}</h3>
                    <p>${course.description}</p>
                    <p><strong>Price:</strong> $${course.price}</p>
                `;
                coursesDiv.appendChild(courseDiv);
            });

            courseList.classList.remove("hidden");
            hideCoursesBtn.classList.remove("hidden"); // Показать кнопку скрытия
        } catch (error) {
            console.error(error);
            alert("Error fetching courses");
        }
    });

    hideCoursesBtn.addEventListener("click", () => {
        courseList.classList.add("hidden");
        hideCoursesBtn.classList.add("hidden");
    });

    createCourseBtn.addEventListener("click", () => {
        createCourseForm.classList.toggle("hidden");
    });

    courseForm.addEventListener("submit", async (event) => {
        event.preventDefault();

        const courseData = {
            name: document.getElementById("course-name").value,
            description: document.getElementById("course-description").value,
            price: parseFloat(document.getElementById("course-price").value),
            courseCreatorId: parseInt(document.getElementById("course-creator-id").value),
        };

        try {
            const response = await fetch("http://localhost:8080/platform/course", {
                method: "PUT",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify(courseData),
            });

            if (!response.ok) throw new Error("Failed to create course");

            alert("Course created successfully!");
            courseForm.reset();
            createCourseForm.classList.add("hidden");
        } catch (error) {
            console.error(error);
            alert("Error creating course");
        }
    });
});
