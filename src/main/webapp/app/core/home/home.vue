<template>
  <div class="home h-100">
    <div class="col-md-12 px-0">
      <div class="row banner w-100 mx-0 d-flex justify-content-center align-items-center h-50">
            <div class="title mt-0 col-md-6 col-sm-12 d-flex flex-column align-items-center">
              <h1 class="display-3 text-center font-weight-bold text-primary" v-text="$t('home.title')">InfluSuccess!</h1>
              <p class="lead text-info text-center" v-text="$t('home.subtitle')"></p>
            </div>
      </div>
      <div class="container my-5">
        <div class="row d-flex flex-column align-items-center justify-content-center my-5">
          <!-- <p class="lead text-center my-5">Ayez accès un grand nombre d'influenceurs pour des collaborations enrichissantes</p> -->
          <div>
            <b-card-group deck>
              <b-card
                header="featured"
                header-tag="header"
                footer="Card Footer"
                footer-tag="footer"
                title="Title"
              >
                <b-card-text>Header and footers using props.</b-card-text>
                <b-button href="#" variant="primary">Go somewhere</b-button>
              </b-card>

              <b-card title="Title" header-tag="header" footer-tag="footer">
                <template #header>
                  <h6 class="mb-0">Header Slot</h6>
                </template>
                <b-card-text>Header and footers using slots.</b-card-text>
                <b-button href="#" variant="primary">Go somewhere</b-button>
                <template #footer>
                  <em>Footer Slot</em>
                </template>
              </b-card>
            </b-card-group>
          </div>
          <div class="row card-container my-5">
            <!-- <div class="col-md-4">
              <div class="profile-card-6"><img src="/content/images/homepage_influencer_1.jpeg" class="img img-responsive">
                  <div class="profile-name">JOHN
                      <br>DOE</div>
                  <div class="profile-overview">
                      <div class="profile-overview">
                          <div class="row text-center d-flex justify-content-around">
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
                <div class="profile-card-6"><img src="/content/images/homepage_influencer_2.jpeg" class="img img-responsive">
                    <div class="profile-name">JANE
                        <br>SMITH</div>
                    <div class="profile-overview">
                      <div class="profile-overview">
                          <div class="row text-center d-flex justify-content-around">
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
            <div class="col-md-4 mb-5">
                <div class="profile-card-6"><img src="/content/images/homepage_influencer_3.jpeg" class="img img-responsive">
                    <div class="profile-name">SARA
                        <br>FLYNN</div>
                    <div class="profile-position">Lorem Ipsum Donor</div>
                    <div class="profile-overview">
                        <div class="profile-overview">
                            <div class="row text-center d-flex justify-content-around">
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
            </div> -->
          </div>
        </div>
      </div>
      <div class="description-section-container container my-5">
        <div class="description-section-campaign row d-flex flex-md-row flex-md-column justify-content-between" id="description-section-campaign">
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
  min-height: 40vh;
  height: 70vh;
  position: relative;
  background-color: #E8EAF6;
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

</style>