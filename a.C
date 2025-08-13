#include <stdio.h>
#include <stdlib.h>
#include "a.h"
typedef struct node{
    int val;
    struct node *left;
    struct node *right;
}Bt;
int inorder(Bt *root){
    if(root==NULL){
        return 0;
    }
    else{
        inorder(root->left);
        printf("%d ",root->val);      
        inorder(root->right);
    }
    
}
int main(){
    Bt * root=(Bt *)malloc(sizeof(Bt));
    Bt *rl=(Bt *)malloc(sizeof(Bt));
    Bt *rr=(Bt *)malloc(sizeof(Bt));
    Bt *rll=(Bt *)malloc(sizeof(Bt));
    Bt *rlr=(Bt *)malloc(sizeof(Bt));
    Bt *rrl=(Bt *)malloc(sizeof(Bt));
    Bt *rrr=(Bt *)malloc(sizeof(Bt));
    root->left=rl;
    root->right=rr;
    rl->left=rll;
    rl->right=rlr;
    rr->left=rrl;
    rr->right=rrr;
    root->val=4;
    rl->val=3;
    rll->val=1;
    rlr->val=2;
    rr->val=5;
    rrl->val=6;
    rrr->val=7;
    inorder(root);
    return 0;
}