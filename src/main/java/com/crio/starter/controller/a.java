XMemeAssessment.test_2_get_single_meme ____________________

self = <main.XMemeAssessment testMethod=test_2_get_single_meme>

    @pytest.mark.run(order=3)
    def test_2_get_single_meme(self):  # Score 6

"""Post a new MEME, capture its Id, and verify its GET /meme/{id} returns correct MEME"""
        endpoint = 'memes/'
        
body = {
            'name': 'crio-user' + "9999",
            'caption': 'crio-meme' + "9999",
            'url': self.SAMPLE_URL + self.FIRST_POST
        }

response = self.post_api(endpoint, json.dumps(body))
      
  # print("verify that response status code is one of " + str(self.POSITIVE_STATUS_CODES))
        self.assertIn(response.status_code, self.POSITIVE_STATUS_CODES)
 
       data = self.decode_and_load_json(response)
        # print('First post data: ', data)
    
        # inserted, now get it using get api.
        

endpoint = 'memes/{}'.format(data["id"])
        response = self.get_api(endpoint)
>       
self.assertIn(response.status_code, self.POSITIVE_STATUS_CODES)
E
       AssertionError: 404 not found in [200, 201, 202, 203]

assessment/main.py:149: AssertionError
__________________ 


