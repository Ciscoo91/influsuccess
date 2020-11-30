<template>
  <div class="home h-100">
      <div class="row banner w-100 mx-0 d-flex justify-content-center align-items-center h-50">
            <div class="title mt-0 col-md-6 col-sm-12 d-flex flex-column align-items-center">
              <h1 class="display-3 text-center font-weight-bold text-primary" v-text="$t('home.title')">InfluSuccess!</h1>
              <p class="lead text-info text-center" v-text="$t('home.subtitle')"></p>
            </div>
      </div>
      <div class="card-section container mb-5 pb-5 mt-n5">
          <b-card-group deck class="card-group">
            <b-card
              img-src="https://via.placeholder.com/350x150/303f9f/FFFFFF"
              class="home-card border-0 shadow-sm"
              title="Influenceurs"
              img-top
              align="center"
              >
              <b-card-text>
                Trouvez parmis des milliers de partenariats potentiels pour augmenter votre audiance et votre protefeuille
              </b-card-text>
              <div class="card-icons-container d-flex mt-4">
                <div class="col-6">
                  <img src="/content/images/noun_map_location_3329139.svg" alt="globe_image" style="max-width: 30%;">
                  <p>Trouvez des influenceurs dans le pays de votre choix</p>
                </div>
                <div class="col-6">
                  <img src="/content/images/noun_social_media_2731512.svg" alt="growth_chart" style="max-width: 30%;">
                  <p>Fidélisez vos abonnés en leur permettant de découvrir de nouveaux produits</p>
                </div>
              </div>
              
            </b-card>
            <b-card
              img-src="https://via.placeholder.com/350x150/303f9f/FFFFFF"
              class="home-card border-0 shadow-sm"
              title="Annonceurs"
              img-top
              align="center"
            >
              <b-card-text>
                Postez des annonces et collaborez avec un ou plusieurs influenceurs dans la liste des milliers d'influenceurs que contient notre plateforme
              </b-card-text>

              <div class="card-icons-container d-flex mt-4">
                <div class="col-6">
                  <img src="/content/images/noun_social_media_1279367.svg" alt="globe_image" style="max-width: 30%;">
                  <p>Reforcez l'image de votre marque au près des consommateurs</p>
                </div>
                <div class="col-6">
                  <img src="/content/images/noun_growth_graph_3329127.svg" alt="growth_chart" style="max-width: 30%;">
                  <p>Faitez prosperer votre entreprise</p>
                </div>
              </div>
            </b-card>
          </b-card-group>
      </div>
      <div class="social-icons-section row d-flex flex-column align-items-center justify-content-center py-5 mt-5">
        <p class="display-4 mb-4 text-info">Our Social Medias</p>
        <div class="row d-flex justify-content-center">
          <div class="facebook-card py-4 px-4 shadow-sm rounded bg-secondary d-flex flex-column align-items-center">
            <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-brand-facebook" width="56" height="56" viewBox="0 0 24 24" stroke-width="1.5" stroke="#303f9f" fill="none" stroke-linecap="round" stroke-linejoin="round">
              <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
              <path d="M7 10v4h3v7h4v-7h3l1 -4h-4v-2a1 1 0 0 1 1 -1h3v-4h-3a5 5 0 0 0 -5 5v2h-3" />
            </svg>
            Facebook
          </div>
          <div class="snapchat-card py-4 px-4 mx-3 shadow-sm rounded bg-secondary d-flex flex-column align-items-center">
            <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-brand-snapchat" width="56" height="56" viewBox="0 0 24 24" stroke-width="1.5" stroke="#303f9f" fill="none" stroke-linecap="round" stroke-linejoin="round">
              <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
              <path d="M16.882 7.842a4.882 4.882 0 0 0 -9.764 0c0 4.273 -.213 6.409 -4.118 8.118c2 .882 2 .882 3 3c3 0 4 2 6 2s3 -2 6 -2c1 -2.118 1 -2.118 3 -3c-3.906 -1.709 -4.118 -3.845 -4.118 -8.118zm-13.882 8.119c4 -2.118 4 -4.118 1 -7.118m17 7.118c-4 -2.118 -4 -4.118 -1 -7.118" />
            </svg>
            Snapchat
          </div>
          <div class="instagram-card py-4 px-4 shadow-sm rounded bg-secondary d-flex flex-column align-items-center">
            <svg xmlns="http://www.w3.org/2000/svg" class="icon icon-tabler icon-tabler-brand-instagram" width="56" height="56" viewBox="0 0 24 24" stroke-width="1.5" stroke="#303f9f" fill="none" stroke-linecap="round" stroke-linejoin="round">
              <path stroke="none" d="M0 0h24v24H0z" fill="none"/>
              <rect x="4" y="4" width="16" height="16" rx="4" />
              <circle cx="12" cy="12" r="3" />
              <line x1="16.5" y1="7.5" x2="16.5" y2="7.501" />
            </svg>
            Instagram
          </div>
        </div>
        
      </div>
      <div class="description-section-container container pt-5 my-5">
        <div class="description-section-campaign row d-flex flex-md-row flex-sm-column justify-content-between align-items-sm-center" id="description-section-campaign">
            <img src="/content/images/homepage_social_medias.jpg" class="description_social_image col-md-6 mx-sm-auto" alt="">

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
            <img src="/content/images/homepage_social_influencer.jpg" class="description_social_image col-md-6" alt="">
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

.description_social_image{
  width: 100%;
  height: auto;
  margin: 0 auto;
}

.content{
  line-height:2em;
}

.social-icons-section{
  position:relative;
}

.social-icons-section::after{
  position: absolute;
  bottom: -50px;
  content: "";
  width: 30%;
  margin: 2rem auto;
  border: 1px solid #f5f5f5;
}

.snapchat-card:hover, 
.facebook-card:hover,
.instagram-card{
  background-color: red;
  cursor: pointer;
}

</style>