declare var modelParams;
var model;
$(document).ready(el => {
    model = modelParams;
    updateForm();

});

function updateForm() {
    var form = $("form");
    $(form).append(createAboutMe());
    $(form).append(createEmail());
    $(form).append(createSocialMedias());
    $(form).append("<input type='submit'/>")
}

function createAboutMe(): HTMLTextAreaElement {
    var textarea = document.createElement("textarea");
    textarea.value = model.aboutme;
    textarea.name = "aboutme";
    textarea.placeholder = "about me";
    return textarea;
}

function createEmail(): HTMLInputElement {
    var email = document.createElement("input");
    email.type = "email";
    email.placeholder = "email";
    email.value = model.email;
    email.name = "email";
    return email;
}

function createSocialMedias():HTMLTextAreaElement{
    var textarea = document.createElement("textarea");
    textarea.value = JSON.stringify(model.socialMedias);
    textarea.name = "socialMedias";
    textarea.placeholder = "social medias (json)";
    return textarea;
}