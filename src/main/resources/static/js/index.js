
const form = document.getElementById('form');
const firstName = document.getElementById('firstName');
const LastName = document.getElementById('lastName');
const email = document.getElementById('email');
const password = document.getElementById('password');
const passwordCheck = document.getElementById('passwordCheck');

form.addEventListener('click', (e) => {
	e.preventDefault();
   
	checkInputs();
});

function checkInputs() {
	// trim to remove the whitespaces
	const firstNameValue = firstName.value.trim();
    const lastNameValue = LastName.value.trim();
	const emailValue = email.value.trim();
	const passwordValue = password.value.trim();
	const passwordCheckValue = passwordCheck.value.trim();
	
	if(firstNameValue === '') {
		setErrorFor(firstName, 'First name cannot be blank');
	} else {
		setSuccessFor(firstName);
	}

    if(lastNameValue === '') {
        console.log("hello")
		setErrorFor(LastName, 'Last name cannot be blank');
        console.log('Last name cannot be blank')
	} else {
		setSuccessFor(LastName);
	}
	
	if(emailValue === '') {
		setErrorFor(email, 'Email cannot be blank');
	} else if (!isEmail(emailValue)) {
		setErrorFor(email, 'Not a valid email');
	} else {
		setSuccessFor(email);
	}
	
	if(passwordValue === '') {
		setErrorFor(password, 'Password cannot be blank');
	} else {
		setSuccessFor(password);
	}
	
	if(passwordCheckValue === '') {
		setErrorFor(passwordCheck, 'Password confirmation cannot be blank');
	} else if(passwordValue !== passwordCheckValue) {
		setErrorFor(passwordCheck, 'Passwords does not match');
	} else{
		setSuccessFor(passwordCheck);
	}
}

function setErrorFor(input, message) {
	const formControl = input.parentElement;
	const small = formControl.querySelector('small');
	input.className = 'form-control formControlError';
	small.innerText = message;
}

function setSuccessFor(input) {
	input.className = 'form-control formControlSuccess';
}
	
function isEmail(email) {
	return /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/.test(email);
}