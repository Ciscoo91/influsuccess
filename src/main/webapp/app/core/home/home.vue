<template>
  <div class="home h-100">
    <div class="col-md-12 px-0">
      <div class="row banner w-100 mx-0 d-flex justify-content-center align-items-center h-50">
            <div class="title mt-5 col-md-6 col-sm-12 d-flex flex-column align-items-center">
              <h1 class="display-3 text-center font-weight-bold text-white" v-text="$t('home.title')">InfluSuccess!</h1>
              <p class="lead text-white text-center" v-text="$t('home.subtitle')"></p>
            </div>
      </div>
      <div class="container mt-4">
        <div class="row d-flex justify-content-center">
          <div class="col-md-4">
            <div class="profile-card-6"><img src="http://envato.jayasankarkr.in/code/profile/assets/img/profile-6.jpg" class="img img-responsive">
                <div class="profile-name">JOHN
                    <br>DOE</div>
                <div class="profile-position">Lorem Ipsum Donor</div>
                <div class="profile-overview">
                    <div class="profile-overview">
                        <div class="row text-center">
                            <div class="col-xs-4">
                                <h3>1</h3>
                                <p>Rank</p>
                            </div>
                            <div class="col-xs-4">
                                <h3>50</h3>
                                <p>Matches</p>
                            </div>
                            <div class="col-xs-4">
                                <h3>35</h3>
                                <p>Goals</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            </div>
              <div class="col-md-4">
                <div class="profile-card-6"><img src="http://envato.jayasankarkr.in/code/profile/assets/img/profile-6.jpg" class="img img-responsive">
                    <div class="profile-name">JOHN
                        <br>DOE</div>
                    <div class="profile-position">Lorem Ipsum Donor</div>
                    <div class="profile-overview">
                      <div class="profile-overview">
                          <div class="row text-center">
                              <div class="col-xs-4">
                                  <h3>1</h3>
                                  <p>Rank</p>
                              </div>
                              <div class="col-xs-4">
                                  <h3>50</h3>
                                  <p>Matches</p>
                              </div>
                              <div class="col-xs-4">
                                  <h3>35</h3>
                                  <p>Goals</p>
                              </div>
                          </div>
                      </div>
                  </div>
            </div>
            </div>
              <div class="col-md-4">
                <div class="profile-card-6"><img src="http://envato.jayasankarkr.in/code/profile/assets/img/profile-6.jpg" class="img img-responsive">
                    <div class="profile-name">JOHN
                        <br>DOE</div>
                    <div class="profile-position">Lorem Ipsum Donor</div>
                    <div class="profile-overview">
                        <div class="profile-overview">
                            <div class="row text-center">
                                <div class="col-xs-4">
                                    <h3>1</h3>
                                    <p>Rank</p>
                                </div>
                                <div class="col-xs-4">
                                    <h3>50</h3>
                                    <p>Matches</p>
                                </div>
                                <div class="col-xs-4">
                                    <h3>35</h3>
                                    <p>Goals</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
      </div>
      <div class="description-section-container container my-5">
        <div class="description-section-campaign row d-flex justify-content-between" id="description-section-campaign">
            <img src="/content/images/homepage_social_medias.jpg" class="description_social_marketing col-md-6" alt="">

            <div class="content col-md-6">
              <h3>Entreprise, pourquoi investir dans le marketing d'influence ?</h3>
              <p>
                <ul>
                  <li>4 consommateurs sur 5 achètent un produit qui leur aété recommandé</li>
                  <li>70% des adolescents font plus confiance aux influenceurs qu'aux stars</li>
                  <li>1$ investi dans les marketing d'influence rapporte 5,20$</li>
                </ul>
                source: agence-ldnp
              </p>
            </div>
        </div>
        <div class="description-section-influencer row d-flex justify-content-between my-5" id="description-section-influencer">
            <div class="content col-md-6">
              <h3>Influenceur, pourquoi se lancer dans le marketing d'influence ?</h3>
              <div>
                <p>
                  Lorem ipsum, dolor sit amet consectetur adipisicing elit. Aspernatur corrupti vero tempore officia, maxime sint? Et, adipisci. Expedita, unde voluptatem eos ea minima voluptatum in vel neque nam consequatur dicta.
                </p>
                <ul>
                  <li>La moitié des influenceurs reçoit au moins 4 propositions de marques par mois</li>
                  <li>36% des marques font appel à des micro-influenceurs</li>
                </ul>
              </div>
            </div>
            <img src="/content/images/homepage_social_influencer.jpg" class="description_social_marketing col-md-6" alt="">
        </div>
      </div>
    </div>
  </div>
</template>

<script lang="ts" src="./home.component.ts">
</script>

<style scoped>
.banner {
  min-height: 70vh;
  height: 70vh;
  position: relative;
  background-image: url('/content/images/homepage-banner.jpg');
  background-size: cover;
}

.banner:before {
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background: rgba(0, 0, 0, 0.5);
}

.title {
  z-index: 2;
}

.title .lead {
  line-height: 1.2em;
}

.login-form {
  box-shadow: rgba(255, 255, 255, 0.7);
}

.description_social_marketing{
  width: 50%;
}

.content{
  line-height:2em;
}



.profile-card-6 {
    max-width: 300px;
    background-color: #FFF;
    border-radius: 5px;
    box-shadow: 0px 0px 25px rgba(0, 0, 0, 0.1);
    overflow: hidden;
    position: relative;
    margin: 10px auto;
    cursor: pointer;
}

.profile-card-6 img {
    transition: all 0.15s linear;
}

.profile-card-6 .profile-name {
    position: absolute;
    top: 10px;
    left: 10px;
    font-size: 25px;
    font-weight: bold;
    color: #FFF;
    padding: 15px 20px;
    background: linear-gradient(140deg, rgba(0, 0, 0, 0.4) 50%, rgba(255, 255, 0, 0) 50%);
    transition: all 0.15s linear;
}

.profile-card-6 .profile-position {
    position: absolute;
    color: rgba(255, 255, 255, 0.4);
    left: 30px;
    top: 100px;
    transition: all 0.15s linear;
}

.profile-card-6 .profile-overview {
    position: absolute;
    bottom: 0px;
    left: 0px;
    right: 0px;
    background: linear-gradient(0deg, rgba(0, 0, 0, 0.4) 50%, rgba(255, 255, 0, 0));
    color: #FFF;
    padding: 50px 0px 20px 0px;
    transition: all 0.15s linear;
}

.profile-card-6 .profile-overview h3 {
    font-weight: bold;
}

.profile-card-6 .profile-overview p {
    color: rgba(255, 255, 255, 0.7);
}

.profile-card-6:hover img {
    filter: brightness(80%);
}

.profile-card-6:hover .profile-name {
    padding-left: 25px;
    padding-top: 20px;
}

.profile-card-6:hover .profile-position {
    left: 40px;
}

.profile-card-6:hover .profile-overview {
    padding-bottom: 25px;
}

</style>