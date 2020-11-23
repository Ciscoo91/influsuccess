<template>
  <div class="home h-100">
      <div class="row banner w-100 mx-0 d-flex justify-content-center align-items-center h-50">
            <div class="title mt-0 col-md-6 col-sm-12 d-flex flex-column align-items-center">
              <h1 class="display-3 text-center font-weight-bold text-primary" v-text="$t('home.title')">InfluSuccess!</h1>
              <p class="lead text-info text-center" v-text="$t('home.subtitle')"></p>
            </div>
      </div>
      <div class="card-section container mb-5 mt-n5">
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
      <div class="description-section-container container my-5">
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

</style>