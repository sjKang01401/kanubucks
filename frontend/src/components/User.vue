<template>

<v-card style="width:300px; margin-left:5%;" outlined>
    <template slot="progress">
      <v-progress-linear
        color="deep-purple"
        height="10"
        indeterminate
      ></v-progress-linear>
    </template>

<!--  style="width:290px; height:150px; border-radius:10px; position:relative; "-->
    <v-img
      style="width: auto; height: auto; max-width: 100px; max-height: 100px; margin-left:5px; top:15px;"
      src="https://cdn-icons.flaticon.com/png/512/3177/premium/3177440.png?token=exp=1634576301~hmac=4a9c23416f8447db59ab39b626119730"
    ></v-img>

    <v-card-title v-if="value._links">
        User # {{value._links.self.href.split("/")[value._links.self.href.split("/").length - 1]}}
    </v-card-title>
    <v-card-title v-else>
        User
    </v-card-title>

    <v-card-text style="margin-left:-15px; margin-top:10px;">

          <div class="grey--text ml-4" v-if="editMode" style = "margin-top:-20px;">
            <v-text-field label="Name" v-model="value.name"/>
          </div>
          <h5 v-else>
            Name :  {{value.name }}
          </h5>

          <div class="grey--text ml-4" v-if="editMode" style = "margin-top:-20px;">
            <v-text-field label="Phone" v-model="value.phone"/>
          </div>
          <h5 v-else>
            Phone :  {{value.phone }}
          </h5>

          <div class="grey--text ml-4" v-if="editMode" style = "margin-top:-20px;">
            <v-text-field label="Email" v-model="value.email"/>
          </div>
          <h5 v-else>
            Email :  {{value.email }}
          </h5>

          <div class="grey--text ml-4" v-if="editMode" style = "margin-top:-20px;">
            <v-text-field type="number" label="Stamp" v-model="value.stamp"/>
          </div>
          <h5 v-else>
            Stamp :  {{value.stamp }}
          </h5>

          <div class="grey--text ml-4" v-if="editMode" style = "margin-top:-20px;">
            <v-text-field label="LoginStatus" v-model="value.loginStatus"/>
          </div>
          <h5 v-else>
            LoginStatus :  {{value.loginStatus }}
          </h5>
    </v-card-text>


    <v-card-actions style = "position:absolute; right:0; bottom:0;">
      <v-btn
        color="deep-purple lighten-2"
        text
        @click="edit"
        v-if="!editMode"
      >
        Edit
      </v-btn>
      <v-btn
        color="deep-purple lighten-2"
        text
        @click="save"
        v-else
      >
        Save
      </v-btn>
      <v-btn
        color="deep-purple lighten-2"
        text
        @click="remove"
        v-if="!editMode"
      >
        Delete
      </v-btn>
    </v-card-actions>
  </v-card>


</template>

<script>
  const axios = require('axios').default;

  export default {
    name: 'User',
    props: {
      value: Object,
      editMode: Boolean,
      isNew: Boolean
    },
    data: () => ({
        date: new Date().toISOString().substr(0, 10),
    }),

    methods: {
      edit(){
        this.editMode = true;
      },
      async save(){
        try{
          var temp = null;

          if(this.isNew){
            temp = await axios.post(axios.fixUrl('/users'), this.value)
          }else{
            temp = await axios.put(axios.fixUrl(this.value._links.self.href), this.value)
          }

          this.value = temp.data;

          this.editMode = false;
          this.$emit('input', this.value);

          if(this.isNew){
            this.$emit('add', this.value);
          }else{
            this.$emit('edit', this.value);
          }

        }catch(e){
          alert(e.message)
        }
      },
      async remove(){
        try{
          await axios.delete(axios.fixUrl(this.value._links.self.href))
          this.editMode = false;
          this.isDeleted = true;

          this.$emit('input', this.value);
          this.$emit('delete', this.value);

        }catch(e){
          alert(e.message)
        }
      },

    }
  }
</script>

