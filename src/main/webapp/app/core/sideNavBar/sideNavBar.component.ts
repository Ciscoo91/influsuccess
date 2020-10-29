import { Component, Prop, Vue } from 'vue-property-decorator';

@Component
export default class SideNavBarComponent extends Vue {
  @Prop()
  menu: any[];

  menuItems: string[] = ['search', 'campaign', 'discussion'];

  onClickItem(item) {
    this.$emit('currentComponent', item);
  }
}
