# KDS

A lightweight kitchen display system which is still quite unstable. It has a client, a server and a webservice that has integration with delivery services. Currently the only supported delivery service is ifood, but any other service can be implemented.

It's supposed to be used locally, but can also be used on the web.

## Features 
- **Orders**: Orders can be interacted with, it can be updated to confirmed, preparing, confirmed or canceled, which sends to delivery services the order status;
- **Archive**: Orders that are set as completed are archived;
